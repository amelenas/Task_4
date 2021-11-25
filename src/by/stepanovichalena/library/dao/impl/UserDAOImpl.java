package by.stepanovichalena.library.dao.impl;

import by.stepanovichalena.library.dao.source.BookSource;
import by.stepanovichalena.library.dao.source.exception.ReaderDAOException;
import by.stepanovichalena.library.dao.source.reader.TXTReader;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private static final String USERS_SOURCE_PATH = "users.txt.source.path";
    private BookSource bookSource = new TXTReader();
    private static TreeSet<User> users;
    private String delimiter = "/";

    public UserDAOImpl() {
    }

    @Override
    public boolean register(User user) throws LibraryDAOException {
        download();
        if (user != null && !users.contains(user)) {
            if (user.getAccessLevel() != AccessLevel.ADMIN) {
                user.setAccessLevel(AccessLevel.USER);
            }
            users.add(user);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean logIn(User user) throws LibraryDAOException {
        download();
        if (user == null || user.getName() == null){
            return false;
        }
        if (users.contains(user)) {
            User u = users.ceiling(user);
            if (u != null) {
                return u.getPassword().equals(user.getPassword());
            }
        }
        return false;
    }

    @Override
    public boolean isLevelAdmin(User user) {
        User tmpUser;
        boolean result = false;
        if (users.contains(user)) {
            tmpUser = users.ceiling(user);
            if (tmpUser != null) {
                result = tmpUser.getAccessLevel().equals(AccessLevel.ADMIN);
            }
        }
        return result;
    }


    private void upload() throws LibraryDAOException {
        ArrayList <String> result = new ArrayList<>();
        try {
            for (User user : users) {
                StringBuilder line = new StringBuilder();
                result.add(line.append(user.getName()).append(delimiter).append(user.getPassword()).append(delimiter).append(user.getAccessLevel()).append("\n").toString());
                bookSource.write(USERS_SOURCE_PATH, result, false);
            }
        } catch (ReaderDAOException e) {

            throw new LibraryDAOException("Exception in UserLogicImpl while upload", e);
        }
    }

    private void download() throws LibraryDAOException {
        try {
            parseStringLines(bookSource.readAll(USERS_SOURCE_PATH));
        } catch (ReaderDAOException e) {
            throw new LibraryDAOException("Exception in UserLogicImpl while download", e);
        }
    }

    private void parseStringLines(List<String> textLines) {
        users = new TreeSet<>();
        for (String line : textLines) {
            User user = parse(line);
            users.add(user);
        }
    }

    private User parse(String line) {
        User user = new User();
        if (line != null) {
            String[] array = line.split(delimiter);
            if (array.length > 2) {
                user.setName(array[0]);
                user.setPassword(array[1]);
                user.setAccessLevel(AccessLevel.valueOf(array[2].trim().toUpperCase()));
            }
        }
        return user;
    }

    public void setBookSource(BookSource bookSource) {
        this.bookSource = bookSource;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}

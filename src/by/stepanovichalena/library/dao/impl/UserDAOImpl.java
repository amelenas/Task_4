package by.stepanovichalena.library.dao.impl;

import by.stepanovichalena.library.dao.source.LibraryReader;
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
    private LibraryReader bookSource = new TXTReader();
    private static TreeSet<User> users;
    private String delimiter = "/";

    public UserDAOImpl() {
    }

    @Override
    public boolean register(User user) throws LibraryDAOException {
        download();
        if (user != null && !users.contains(user)) {
            user.setAccessLevel(AccessLevel.USER);
            users.add(user);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public User logIn(User user) throws LibraryDAOException {
        download();
        User tempUser = new User(" ", " ", AccessLevel.DEFAULT);
        if (user == null || user.getName() == null) {
            throw new LibraryDAOException("User is null");
        }
        if (users.contains(user)) {
            User u = users.ceiling(user);
            if (u != null && u.getPassword().equals(user.getPassword())) {
                tempUser = u;
            }
        }
        return tempUser;
    }


    @Override
    public boolean changeUserLevel(User user) throws LibraryDAOException {
        download();
        boolean result = false;
        if (user == null || user.getName() == null) {
            return false;
        }
        for (User u : users) {
            if (u.getName().equals(user.getName())) {
                u.setAccessLevel(user.getAccessLevel());
                users.add(u);
                upload();
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean userSearch(User user) throws LibraryDAOException {
        download();
        if (user == null || user.getName() == null) {
            return false;
        }
        return users.contains(user);
    }


    private void upload() throws LibraryDAOException {
        ArrayList<String> result = new ArrayList<>();
        try {
            for (User user : users) {
                StringBuilder line = new StringBuilder();
                result.add(line.append(user.getName()).append(delimiter).append(user.getPassword()).append(delimiter).append(user.getAccessLevel()).append("\n").toString());
                bookSource.write(USERS_SOURCE_PATH, result, false);
            }
        } catch (ReaderDAOException e) {
            LOGGER.warn("Exception in UserLogicImpl while upload", e);
            throw new LibraryDAOException("Exception in UserLogicImpl while upload", e);
        }
    }

    private void download() throws LibraryDAOException {
        try {
            parseStringLines(bookSource.readAll(USERS_SOURCE_PATH));
        } catch (ReaderDAOException e) {
            LOGGER.warn("Exception in UserLogicImpl while download", e);
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

    public void setBookSource(LibraryReader bookSource) {
        this.bookSource = bookSource;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}

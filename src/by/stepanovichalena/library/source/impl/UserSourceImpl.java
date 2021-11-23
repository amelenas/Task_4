package by.stepanovichalena.library.source.impl;

import by.stepanovichalena.library.source.UserSource;
import by.stepanovichalena.library.source.exception.DAOException;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UserSourceImpl implements UserSource {
    private static final Logger LOGGER = LogManager.getLogger(UserSourceImpl.class);

    private static final String PATH_TO_USERS_LIST = "resource/users.txt";
    private final static String DIVIDER = "/";

    public UserSourceImpl() {
    }

    @Override
    public Collection<User> readAll() throws DAOException {
        Set<User> users = new TreeSet<>();
        List<String> textLines;
        try {
            textLines = Files.readAllLines(Paths.get(PATH_TO_USERS_LIST));
        } catch (IOException e) {
            throw new DAOException("Can't read file", e);
        }
        for (String line : textLines) {
            User user = parse(line);
           if (user.getName()!= null){
                users.add(user);}
        }
        return users;
    }

    @Override
    public void writeAll(Collection<User> users) throws DAOException {
        if (users != null) {
            try (FileOutputStream writer = new FileOutputStream(PATH_TO_USERS_LIST, false)) {
                for (User user : users) {
                    String str = String.join(DIVIDER, user.getName(), user.getPassword(), user.getAccessLevel().toString(), "\n");
                    writer.write(str.getBytes());
                }
            } catch (IOException e) {
                LOGGER.error("Exception in UserSourceImpl while trying to write ", e);
                throw new DAOException("Can't write file", e);
            }
        }
    }

    private User parse(String line) {
        User user = new User();
        if (line != null) {
            String[] array = line.split(DIVIDER);
            if (array.length > 2) {
                user.setName(array[0]);
                user.setPassword(array[1]);
                user.setAccessLevel(AccessLevel.valueOf(array[2].trim().toUpperCase()));
            }
        }
        return user;
    }
}


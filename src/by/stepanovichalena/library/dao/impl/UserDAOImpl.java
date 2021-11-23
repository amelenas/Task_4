package by.stepanovichalena.library.dao.impl;

import by.stepanovichalena.library.source.UserSource;
import by.stepanovichalena.library.source.exception.DAOException;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.UserHolder;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;

import java.util.TreeSet;

public class UserDAOImpl implements UserDAO {
    private static final TreeSet<User> users = new TreeSet<>();
    private UserSource userSource;

    public UserDAOImpl() {
    }

    @Override
    public void connectUserSource(UserSource userSource) throws LibraryDAOException {
        this.userSource = userSource;
        download();
    }

    @Override
    public boolean register(User user) throws LibraryDAOException {
        if (user != null && !users.contains(user)) {
            if (user.getAccessLevel() != AccessLevel.ADMIN) {
                user.setAccessLevel(AccessLevel.USER);
            }
            users.add(user);
            upload();
            setUser(user);
            return true;
        }
        return false;
    }

    private void setUser(User user) {
        UserHolder.setUser(user);
    }

    @Override
    public boolean logIn(User user) {
        if (users.contains(user)) {
            User u = users.ceiling(user);
            if (u != null) {
                setUser(u);
                return u.getPassword().equals(user.getPassword());
            }
        }
        return false;
    }


    private void download() throws LibraryDAOException {
        try {
            users.addAll(userSource.readAll());
        } catch (DAOException e) {
            throw new LibraryDAOException("Exception in UserLogicImpl while download", e);
        }
    }

    private void upload() throws LibraryDAOException {
        try {
            userSource.writeAll(users);
        } catch (DAOException e) {
            throw new LibraryDAOException("Exception in UserLogicImpl while upload", e);
        }
    }
}

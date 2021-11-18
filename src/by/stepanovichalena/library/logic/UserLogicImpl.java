package by.stepanovichalena.library.logic;

import by.stepanovichalena.library.dao.UserDAOImpl;
import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.dao.util.UserDAO;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.logic.util.UserLogic;

import java.util.TreeSet;

public class UserLogicImpl implements UserLogic {
    private static final TreeSet<User> users = new TreeSet<>();
    private UserDAO userDAO;

    public UserLogicImpl() throws LogicException {
        userDAO = UserDAOImpl.getInstance();
        download();
    }

    @Override
    public boolean register(User user) throws LogicException {
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

    private void download() throws LogicException {
        try {
            users.addAll(userDAO.readAll());
        } catch (DAOException e) {
            throw new LogicException("Exception in UserLogicImpl while download", e);
        }
    }

    private void upload() throws LogicException {
        try {
            userDAO.writeAll(users);
        } catch (DAOException e) {
            throw new LogicException("Exception in UserLogicImpl while upload", e);
        }
    }
}

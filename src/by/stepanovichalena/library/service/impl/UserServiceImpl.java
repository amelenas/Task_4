package by.stepanovichalena.library.service.impl;

import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserDAO userDAO;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean register(User user) throws ServiceException {
        boolean result;
        try {
            result = userDAO.register(user);
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception in UserServiceImpl during user registration ", e);
            throw new ServiceException("Exception in UserServiceImpl during user registration ", e);
        }
        return result;
    }

    @Override
    public boolean logIn(User user) throws ServiceException {
        try {
            return userDAO.logIn(user);
        } catch (LibraryDAOException e) {
            throw new ServiceException("Exception in UserServiceImpl while logging in", e);
        }
    }

    @Override
    public boolean findUser(User user) throws ServiceException {
        boolean result;
        try {
            result = userDAO.userSearch(user);
        } catch (LibraryDAOException e) {
            throw new ServiceException("Exception in UserServiceImpl while searching user", e);
        }
        return result;
    }

    @Override
    public boolean isAccessLevelAdmin(User user) {
        return userDAO.isLevelAdmin(user);
    }

    @Override
    public boolean changeAccessLevel(User user) throws ServiceException {
        boolean result;
        try {
            result = userDAO.changeUsersLevel(user);
        } catch (LibraryDAOException e) {
            throw new ServiceException("Exception in UserServiceImpl while changing access user's level", e);
        }
        return result;
    }
}

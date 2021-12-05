package by.stepanovichalena.library.service.impl;

import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.validation.UserValidation;
import by.stepanovichalena.library.validation.impl.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    UserValidation userValidation = new UserValidator();
    private UserDAO userDAO;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean register(User user) throws ServiceException {
        boolean result;
        if (user == null) return false;
        if (!userValidation.isUserNameValid(user.getName())) return false;
        try {
            result = userDAO.register(user);
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception in UserServiceImpl during user registration ", e);
            throw new ServiceException("Exception in UserServiceImpl during user registration ", e);
        }
        return result;
    }

    @Override
    public User logIn(User user) throws ServiceException {
        if (user == null) {
            throw new ServiceException("User data is invalid");
        }
        if (!userValidation.isUserNameValid(user.getName())) {
            throw new ServiceException("User data is invalid");
        }
        try {
            return userDAO.logIn(user);
        } catch (LibraryDAOException e) {
            throw new ServiceException("Exception in UserServiceImpl while logging in", e);
        }
    }

    @Override
    public boolean findUser(User user) throws ServiceException {
        boolean result;
        if (!userValidation.isUserNameValid(user.getName())) return false;
        try {
            result = userDAO.userSearch(user);
        } catch (LibraryDAOException e) {
            throw new ServiceException("Exception in UserServiceImpl while searching user", e);
        }
        return result;
    }

    @Override
    public boolean changeAccessLevel(User user) throws ServiceException {
        boolean result;
        if (user == null) return false;
        if (!userValidation.isUserNameValid(user.getName())) return false;
        try {
            result = userDAO.changeUserLevel(user);
        } catch (LibraryDAOException e) {
            throw new ServiceException("Exception in UserServiceImpl while changing access user's level", e);
        }
        return result;
    }

    public void setUserValidation(UserValidation userValidation) {
        this.userValidation = userValidation;
    }

}

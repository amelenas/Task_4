package by.stepanovichalena.library.service.impl;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.validation.util.UserValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final static String REGISTRATION_OK = "Successfully registered";
    private final static String REGISTRATION_ERROR = "Registration error";
    private final static String LOG_IN_OK = "Welcome back";
    private final static String LOG_IN_ERROR = "Bad login or password";
    private final static String DELIMITER = "/";

    private UserDAO userDAO;
    private UserValidation userValidation;

    public UserServiceImpl() {
    }

    @Override
    public void connectUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String register(String line) throws ServiceException {
        boolean result = false;
        if (line != null) {
            String[] array = line.split(DELIMITER);
            if (array.length > 1) {
                if (userValidation.isUserDataValid(array[0], array[1])) {
                    User user = new User(array[0], hashPassword(array[1]), AccessLevel.DEFAULT);
                    try {
                        result = userDAO.register(user);
                    } catch (LibraryDAOException e) {
                        LOGGER.error("Exception in UserServiceImpl during user registration ", e);
                        throw new ServiceException("Exception in UserServiceImpl during user registration ", e);
                    }
                }
            }
        }
        return result ? REGISTRATION_OK : REGISTRATION_ERROR;
    }

    @Override
    public String logIn(String line) throws ServiceException {
        boolean result = false;
        if (line != null) {
            String[] array = line.split(DELIMITER);
            if (array.length > 1) {
                if (userValidation.isUserDataValid(array[0], array[1])) {
                    User user = new User(array[0], hashPassword(array[1]), AccessLevel.DEFAULT);
                    result = userDAO.logIn(user);
                }
            }
        }
        return result ? LOG_IN_OK : LOG_IN_ERROR;
    }


    private String hashPassword(String password) throws ServiceException {
        StringBuilder hashBuilder = new StringBuilder();
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            for (byte b : hash) {
                hashBuilder.append(b);
            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Exception while hashing password", e);
            throw new ServiceException("Exception while hashing password", e);
        }
        return hashBuilder.toString();
    }

    public void setUserValidation(UserValidation userValidation) {
        this.userValidation = userValidation;
    }
}

package by.stepanovichalena.library.service;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.logic.BookFactory;
import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.logic.util.UserLogic;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.util.UserService;
import by.stepanovichalena.library.service.validation.UserValidator;
import by.stepanovichalena.library.service.validation.util.UserValidation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService {
    private final static String REGISTRATION_OK = "Successfully registered";
    private final static String REGISTRATION_ERROR = "Registration error";
    private final static String LOG_IN_OK = "Welcome back";
    private final static String LOG_IN_ERROR = "Bad login or password";

    private BookFactory bookFactory;
    private UserLogic userLogic;
    private UserValidation userValidation = new UserValidator();

    public UserServiceImpl() throws ServiceException {
        try {
            bookFactory = BookFactory.getInstance();
        } catch (LogicException e) {
            throw new ServiceException("Exception in UserServiceImpl while BookFactory.getInstance()", e);
        }
        userLogic = bookFactory.getUserDAO();

    }

    @Override
    public String register(String userName, String password) throws ServiceException {
        boolean result = false;
        if (userValidation.isUserDataValid(userName, password)) {

            User user = new User(userName, hashPassword(password), AccessLevel.DEFAULT);
            try {
                result = userLogic.register(user);
            } catch (LogicException e) {
                throw new ServiceException(e);
            }

        }
        return result ? REGISTRATION_OK : REGISTRATION_ERROR;
    }

    @Override
    public String logIn(String userName, String password) throws ServiceException {
        boolean result = false;
        if (userValidation.isUserDataValid(userName, password)) {
            User user = new User(userName, hashPassword(password), AccessLevel.DEFAULT);
            result = userLogic.logIn(user);
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
            throw new ServiceException("Exception while hashing password", e);
        }
        return hashBuilder.toString();
    }
}

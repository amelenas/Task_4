package by.stepanovichalena.library.service;

import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.logic.LibraryFactory;
import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.logic.util.UserLogic;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.util.UserService;
import by.stepanovichalena.library.service.validation.UserValidator;
import by.stepanovichalena.library.service.validation.util.UserValidation;

public class UserServiceImpl implements UserService {
    private final static String REGISTRATION_OK = "Successfully registered";
    private final static String REGISTRATION_ERROR = "Registration error";
    private final static String LOG_IN_OK = "Welcome back";
    private final static String LOG_IN_ERROR = "Bad login or password";

    LibraryFactory libraryFactory;
    UserLogic userLogic;
    UserValidation userValidation;

    public UserServiceImpl() throws ServiceException {
        try {
            libraryFactory = LibraryFactory.getInstance();
        } catch (LogicException e) {
            throw new ServiceException(e);
        }
        userLogic = libraryFactory.getUserDAO();
        userValidation = UserValidator.getInstance();
    }

    @Override
    public String register(String request) throws ServiceException {
        boolean result;
            User user = userValidation.validateUser(request);
        try {
            result = userLogic.register(user);
        } catch (LogicException e) {
            throw new ServiceException(e);
        }
        return result ? REGISTRATION_OK : REGISTRATION_ERROR;
    }

    @Override
    public String logIn(String request) {
        boolean result;
        User user = userValidation.validateUser(request);
        result = userLogic.logIn(user);
        return result ? LOG_IN_OK : LOG_IN_ERROR;
    }
}

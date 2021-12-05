package by.stepanovichalena.library.controller.impl.actionlist.user;

import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.util.HashPassword;
import by.stepanovichalena.library.util.exeption.LibraryException;
import by.stepanovichalena.library.validation.UserValidation;
import by.stepanovichalena.library.validation.impl.UserValidator;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogIn implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LogIn.class);
    private static final String LOG_IN_OK = " welcome back ";
    private static final String LOG_IN_ERROR = " inputted values is invalid ";
    private static final String INCORRECT_PASSWORD_OR_LOGIN = " incorrect password or login ";
    private UserValidation userValidation = new UserValidator();
    private HashPassword hashPassword = new HashPassword();

    private UserService userSource;
    private String[] requestParameters;

    public LogIn(UserDAO userDAO) {
        this.userSource = ServiceLibraryFactoryImpl.getInstance().getUserService(userDAO);
    }

    @Override
    public String execute() {
        boolean result = false;
        StringBuilder resultLine = new StringBuilder();
        String userName = requestParameters[0];
        String password = requestParameters[1];
        try {
            if (userValidation.isUserDataValid(userName, password)) {
                User tempUser = new User(userName, hashPassword.hashPassword(password), AccessLevel.DEFAULT);
                if (userSource.logIn(tempUser).getName().equals(tempUser.getName())) {
                    UserHolder.setUser(userSource.logIn(tempUser));
                    result = true;
                } else {
                    resultLine.append(INCORRECT_PASSWORD_OR_LOGIN);
                }
            }
        } catch (ServiceException | LibraryException e) {
            LOGGER.warn("Exception in log in command ", e);
            resultLine.append(e.getMessage());
        }
        resultLine.append(result ? LOG_IN_OK : LOG_IN_ERROR);
        return resultLine.toString();
    }

    @Override
    public void request(String... requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void setUserValidation(UserValidation userValidation) {
        this.userValidation = userValidation;
    }

    public void setUserSource(UserService userSource) {
        this.userSource = userSource;
    }

    public void setHashPassword(HashPassword hashPassword) {
        this.hashPassword = hashPassword;
    }
}

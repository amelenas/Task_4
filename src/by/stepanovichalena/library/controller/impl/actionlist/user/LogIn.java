package by.stepanovichalena.library.controller.impl.actionlist.user;

import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.HashPassword;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.UserValidation;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.impl.UserValidator;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogIn implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LogIn.class);
    private static final String LOG_IN_OK = " Welcome back ";
    private static final String LOG_IN_ERROR = " Inputted values is invalid ";
    private UserValidation userValidation = new UserValidator();
    private UserService userSource;
    private String[] requestParameters;

    public LogIn(UserDAO userDAO) {
        this.userSource = ServiceLibraryFactory.getInstance().getUserService(userDAO);
    }

    @Override
    public String execute() {
        boolean result = false;
        StringBuilder resultLine = new StringBuilder();
        String userName = requestParameters[0];
        String password = requestParameters[1];
        LOGGER.info(userName + " " + password);
        try {
            User user = new User(userName, HashPassword.hashPassword(password), AccessLevel.DEFAULT);
            result = userSource.logIn(user);
            boolean resultInfo = userValidation.isUserDataValid(userName, password);
            LOGGER.info("User validation " + resultInfo);
        if (result) {
            if (userSource.isAccessLevelAdmin(user)) {
                user.setAccessLevel(AccessLevel.ADMIN);
                LOGGER.info(user.toString());
            } else {
                user.setAccessLevel(AccessLevel.USER);
                LOGGER.info(user.toString());
            }
            UserHolder.setUser(user);
            LOGGER.info(UserHolder.getUser().toString());
            LOGGER.info(user.toString());
        }
        } catch (ControllerException | ServiceException e) {
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
}

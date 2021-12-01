package by.stepanovichalena.library.controller.impl.actionlist.user;

import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.UserValidation;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.impl.UserValidator;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserLevel implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeUserLevel.class);
    private static final String CHANGE_LEVEL_OK = " user's level changed ";
    private static final String CHANGE_LEVEL_ERROR = " user's level not changed ";
    private static final String INCORRECT_ACCESS_LEVEL = " incorrect access level ";
    private UserValidation userValidation = new UserValidator();
    private UserService userSource;
    private String[] requestParameters;

    public ChangeUserLevel(UserDAO userDAO) {
        this.userSource = ServiceLibraryFactoryImpl.getInstance().getUserService(userDAO);
    }

    @Override
    public String execute() throws ControllerException {
        boolean result = false;
        StringBuilder resultLine = new StringBuilder();
        String userName = requestParameters[0];
        String accessLevel = requestParameters[1];
        try {
            userValidation.isUserNameValid(userName);
            if (UserHolder.getUsersLevel() != AccessLevel.ADMIN) {
                resultLine.append(INCORRECT_ACCESS_LEVEL);
            } else {
                User user = new User(userName, " ", AccessLevel.DEFAULT);
                boolean isFound = userSource.findUser(user);
                if (isFound) {
                    user.setAccessLevel(AccessLevel.valueOf(accessLevel));
                    result = userSource.changeAccessLevel(user);
                }
            }
        } catch (ControllerException | ServiceException e) {
            LOGGER.warn("Exception in log in command ", e);
            resultLine.append(e.getMessage());
        }
        resultLine.append(result ? CHANGE_LEVEL_OK : CHANGE_LEVEL_ERROR);
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

}

package by.stepanovichalena.library.controller.impl.actionlist.user;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.HashPassword;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.impl.UserValidator;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactoryImpl;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.UserValidation;

public class SignUp implements Command {
    private static final String SIGN_UP_OK = " successfully registered ";
    private static final String SIGN_UP_ERROR = " inputted values is invalid ";
    private UserValidation userValidation = new UserValidator();
    private UserService userSource;
    private String[] requestParameters;

    public SignUp(UserDAO userDAO) {
        this.userSource = ServiceLibraryFactoryImpl.getInstance().getUserService(userDAO);
    }

    @Override
    public String execute() throws ControllerException {
        boolean result = false;
        StringBuilder resultLine = new StringBuilder();
        String userName = requestParameters[0];
        String password = requestParameters[1];
        try {
            userValidation.isUserDataValid(userName, HashPassword.hashPassword(password));
            User user = new User(userName, HashPassword.hashPassword(password),AccessLevel.DEFAULT);
            result = userSource.register(user);
        } catch (ControllerException | ServiceException e) {
            resultLine.append(e.getMessage());
        }
        resultLine.append(result ? SIGN_UP_OK : SIGN_UP_ERROR);
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

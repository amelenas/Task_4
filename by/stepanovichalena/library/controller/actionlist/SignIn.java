package by.stepanovichalena.library.controller.actionlist;

import by.stepanovichalena.library.controller.util.Command;
import by.stepanovichalena.library.service.ServiceFactory;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.util.UserService;

public class SignIn implements Command {
    @Override
    public String execute(String request) throws ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getClientService();
        return userService.register(request);
    }
}

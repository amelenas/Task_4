package by.stepanovichalena.library.controller.actionlist;

import by.stepanovichalena.library.controller.util.Command;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.factory.UserDAOFactory;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.factory.ServiceUserFactory;
import by.stepanovichalena.library.source.UserSource;
import by.stepanovichalena.library.source.impl.UserSourceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogIn implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LogIn.class);

    @Override
    public String execute(String request) throws ServiceException {
        UserSource userSource = new UserSourceImpl();
        UserDAO userDAO = UserDAOFactory.getInstance();
        try {
            userDAO.connectUserSource(userSource);
        } catch (LibraryDAOException e) {
            LOGGER.warn(e.getMessage());
            throw new ServiceException("Exception while LogIn", e);
        }
        UserService userService = ServiceUserFactory.getInstance();
        userService.connectUserDAO(userDAO);
        return userService.logIn(request);
    }
}

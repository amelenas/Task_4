package test.stepanovichalena.service;

import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactory;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserDAO userDAO = LibraryDAOFactory.getInstance().getUserDAO();
    UserService userService = ServiceLibraryFactory.getInstance().getUserService(userDAO);

    @Test
    public void register() throws ServiceException {
       assertTrue(userService.register(new User("ServiceUserTest2","Password", AccessLevel.ADMIN)));
    }

    @Test
    public void registerNull() throws ServiceException {
      assertFalse(userService.register(null));
    }

    @Test
    public void logIn() throws ServiceException {
        assertTrue(userService.logIn(new User("ServiceUserTest2","Password", AccessLevel.ADMIN)));
    }

    @Test
    public void logInNull() throws ServiceException {
        assertFalse(userService.logIn(null));
    }
}

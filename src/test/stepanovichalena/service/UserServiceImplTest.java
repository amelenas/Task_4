package test.stepanovichalena.service;

import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactoryImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserDAO userDAO = LibraryDAOFactoryImpl.getInstance().getUserDAO();
    UserService userService = ServiceLibraryFactoryImpl.getInstance().getUserService(userDAO);

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
        System.out.println(userService.logIn(new User("ServiceUserTest2","Password", AccessLevel.ADMIN)));
    }

    @Test (expected = ServiceException.class)
    public void logInNull() throws ServiceException {
        System.out.println(userService.logIn(null));
    }


    @Test
    public void changeAccessLevel() throws ServiceException {
        assertTrue(userService.changeAccessLevel(new User("ServiceUserTest2","", AccessLevel.ADMIN)));
    }

    @Test
    public void changeAccessLevelNull() throws ServiceException {
        assertFalse(userService.changeAccessLevel(null));
    }
}

package test.stepanovichalena.service;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.factory.BookDAOFactory;
import by.stepanovichalena.library.dao.factory.UserDAOFactory;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.factory.ServiceBookFactory;
import by.stepanovichalena.library.service.factory.ServiceUserFactory;
import by.stepanovichalena.library.service.impl.UserServiceImpl;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.source.BookSource;
import by.stepanovichalena.library.source.UserSource;
import by.stepanovichalena.library.source.impl.BookSourceImpl;
import by.stepanovichalena.library.source.impl.UserSourceImpl;
import org.junit.Test;

public class UserServiceImplTest {

    @Test
    public void register() throws LibraryDAOException, ServiceException {
        UserSource userSource = new UserSourceImpl();
        UserDAO userDAO = UserDAOFactory.getInstance();
        userDAO.connectUserSource(userSource);
        UserService userService = ServiceUserFactory.getInstance();
        userService.connectUserDAO(userDAO);
        System.out.println(userService.register("ServiceUserTest/Password"));
    }

    @Test
    public void registerNull() throws LibraryDAOException, ServiceException {
        UserSource userSource = new UserSourceImpl();
        UserDAO userDAO = UserDAOFactory.getInstance();
        userDAO.connectUserSource(userSource);
        UserService userService = ServiceUserFactory.getInstance();
        userService.connectUserDAO(userDAO);
        System.out.println(userService.register(null));
    }

    @Test
    public void logIn() throws ServiceException, LibraryDAOException {
        UserSource userSource = new UserSourceImpl();
        UserDAO userDAO = UserDAOFactory.getInstance();
        userDAO.connectUserSource(userSource);
        UserService userService = ServiceUserFactory.getInstance();
        userService.connectUserDAO(userDAO);
        System.out.println(userService.logIn("ServiceUserTest/Password"));
    }

    @Test
    public void logInNull() throws ServiceException, LibraryDAOException {
        UserSource userSource = new UserSourceImpl();
        UserDAO userDAO = UserDAOFactory.getInstance();
        userDAO.connectUserSource(userSource);
        UserService userService = ServiceUserFactory.getInstance();
        userService.connectUserDAO(userDAO);
        System.out.println(userService.logIn(null));
    }
}
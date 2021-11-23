package test.stepanovichalena.logic;

import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.factory.UserDAOFactory;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.impl.UserDAOImpl;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.source.UserSource;
import by.stepanovichalena.library.source.impl.UserSourceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    @Test
    public void register() throws LibraryDAOException {
        UserSource userSource = new UserSourceImpl();
        UserDAO userDAO = UserDAOFactory.getInstance();
        userDAO.connectUserSource(userSource);
        assertTrue(userDAO.register(new User("Test2", "32165", AccessLevel.ADMIN)));
    }

    @Test
    public void logIn() throws LibraryDAOException {
        UserSource userSource = new UserSourceImpl();
        UserDAO userDAO = UserDAOFactory.getInstance();
        userDAO.connectUserSource(userSource);
        assertTrue(userDAO.logIn(new User("Test2", "32165", AccessLevel.ADMIN)));

    }
    @Test
    public void logInInvalidPassword() throws LibraryDAOException {
        UserSource userSource = new UserSourceImpl();
        UserDAO userDAO = UserDAOFactory.getInstance();
        userDAO.connectUserSource(userSource);
        assertFalse(userDAO.logIn(new User("Test2", "321", AccessLevel.ADMIN)));

    }
}
package test.stepanovichalena.dao;

import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOImplTest {
    LibraryDAOFactoryImpl libraryDAOFactory = LibraryDAOFactoryImpl.getInstance();
    UserDAO userDAO = libraryDAOFactory.getUserDAO();

    @Test
    public void register() throws LibraryDAOException {
        assertTrue(userDAO.register(new User("Test24", "32165", AccessLevel.ADMIN)));
    }

    @Test
    public void registerNull() throws LibraryDAOException {
        assertFalse(userDAO.register(null));
    }

    @Test
    public void logIn() throws LibraryDAOException {
        assertTrue(userDAO.logIn(new User("Test24", "32165", AccessLevel.ADMIN)));
    }

    @Test
    public void logInNull() throws LibraryDAOException {
        assertFalse(userDAO.logIn(null));
    }

    @Test
    public void logInInvalidPassword() throws LibraryDAOException {
        assertFalse(userDAO.logIn(new User("Test2", "321", AccessLevel.ADMIN)));
    }

    @Test
    public void logInInvalidPasswordNull() throws LibraryDAOException {
        assertFalse(userDAO.logIn(null));
    }

    @Test
    public void changeUsersLevel() throws LibraryDAOException {
        assertTrue(userDAO.changeUsersLevel(new User("Test2", "321", AccessLevel.ADMIN)));
    }

    @Test
    public void changeUsersLevelNull() throws LibraryDAOException {
        assertFalse(userDAO.changeUsersLevel(null));
    }
}

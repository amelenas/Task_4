package test.stepanovichalena.dao;

import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactory;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOImplTest {
    LibraryDAOFactory libraryDAOFactory = LibraryDAOFactory.getInstance();
    UserDAO userDAO = libraryDAOFactory.getUserDAO();

    @Test
    public void register() throws LibraryDAOException {
        assertTrue(userDAO.register(new User("Test24", "32165", AccessLevel.ADMIN)));
    }

    @Test
    public void logIn() throws LibraryDAOException {
        assertTrue(userDAO.logIn(new User("Test24", "32165", AccessLevel.ADMIN)));
    }

    @Test
    public void logInInvalidPassword() throws LibraryDAOException {
        assertFalse(userDAO.logIn(new User("Test2", "321", AccessLevel.ADMIN)));
    }
}

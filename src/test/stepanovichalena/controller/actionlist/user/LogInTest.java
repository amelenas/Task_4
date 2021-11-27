package test.stepanovichalena.controller.actionlist.user;

import by.stepanovichalena.library.controller.impl.actionlist.user.LogIn;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class LogInTest {
    LibraryDAOFactoryImpl libraryDAO = LibraryDAOFactoryImpl.getInstance();
    @Test
    public void execute() throws ServiceException {
        LogIn logIn = new LogIn(libraryDAO.getUserDAO());
        logIn.request("Goggi", "Goggi");
        System.out.println(logIn.execute());
    }
    @Test
    public void executeWrongPassword(){
        LogIn logIn = new LogIn(libraryDAO.getUserDAO());
        logIn.request("Goggi", "GoggiGoggi");
        System.out.println(logIn.execute());
    }

    @Test
    public void executeNull2() {
        LogIn logIn = new LogIn(libraryDAO.getUserDAO());
        logIn.request(null, null);
        System.out.println(logIn.execute());
    }
}

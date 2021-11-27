package test.stepanovichalena.controller.actionlist.user;

import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.user.ChangeUserLevel;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import org.junit.Test;

public class ChangeUserLevelTest {
    LibraryDAOFactoryImpl libraryDAO = LibraryDAOFactoryImpl.getInstance();

    @Test
    public void executeAdmin() throws ControllerException {
        UserHolder.setUser(new User("Goggi", "Goggi", AccessLevel.ADMIN));
        ChangeUserLevel changeUserLevel = new ChangeUserLevel(libraryDAO.getUserDAO());
        changeUserLevel.request("ServiceUserTest", "ADMIN");
        System.out.println(changeUserLevel.execute());
    }

    @Test
    public void executeUser() throws ControllerException {
        UserHolder.setUser(new User("Kosm", "36987", AccessLevel.USER));
        ChangeUserLevel changeUserLevel = new ChangeUserLevel(libraryDAO.getUserDAO());
        changeUserLevel.request("TestAddBookAdmin", "ADMIN");
        System.out.println(changeUserLevel.execute());
    }

    @Test
    public void executeNull () throws ControllerException {
        UserHolder.setUser(new User("Goggi", "Goggi", AccessLevel.ADMIN));
        ChangeUserLevel changeUserLevel = new ChangeUserLevel(libraryDAO.getUserDAO());
        changeUserLevel.request(null, null);
        System.out.println(changeUserLevel.execute());
    }
}

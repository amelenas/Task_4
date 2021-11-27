package test.stepanovichalena.controller.actionlist.book;

import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.DeleteBookById;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import org.junit.Test;

public class DeleteBookByIdTest {
    LibraryDAOFactoryImpl libraryDAO = LibraryDAOFactoryImpl.getInstance();

    @Test
    public void executeAdmin() throws ControllerException {
        UserHolder.setUser(new User("Goggi", "Goggi", AccessLevel.ADMIN));
        DeleteBookById deleteBookById = new DeleteBookById(libraryDAO.getBookDAO());
        deleteBookById.request("1");
        System.out.println(deleteBookById.execute());
    }

    @Test
    public void executeUser() throws ControllerException {
        UserHolder.setUser(new User("Kosm", "36987", AccessLevel.USER));
        DeleteBookById deleteBookById = new DeleteBookById(libraryDAO.getBookDAO());
        deleteBookById.request("24");
        System.out.println(deleteBookById.execute());
    }

    @Test
    public void executeNull () throws ControllerException {
        UserHolder.setUser(new User("Goggi", "Goggi", AccessLevel.ADMIN));
        DeleteBookById deleteBookById = new DeleteBookById(libraryDAO.getBookDAO());
        deleteBookById.request(null);
        System.out.println(deleteBookById.execute());
    }
}

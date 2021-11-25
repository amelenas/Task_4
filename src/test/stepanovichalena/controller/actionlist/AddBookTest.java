package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.AddBook;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactory;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import org.junit.Test;

public class AddBookTest {
    LibraryDAOFactory libraryDAO = LibraryDAOFactory.getInstance();

    @Test
    public void executeAdmin() throws ControllerException {
        UserHolder.setUser(new User("Goggi", "Goggi", AccessLevel.ADMIN));
        AddBook addBook = new AddBook(libraryDAO.getBookDAO());
        addBook.request("TestAddBookAdmin", "TestAddBook");
        System.out.println(addBook.execute());
    }

    @Test
    public void executeUser() throws ControllerException {
        UserHolder.setUser(new User("Kosm", "36987", AccessLevel.USER));
        AddBook addBook = new AddBook(libraryDAO.getBookDAO());
        addBook.request("TestAddBookUser", "TestAddBook");
        System.out.println(addBook.execute());
    }

    @Test
    public void titleNumber() throws ControllerException {
        UserHolder.setUser(new User("Goggi", "Goggi", AccessLevel.ADMIN));
        AddBook addBook = new AddBook(libraryDAO.getBookDAO());
        addBook.request("999999", "TestAddBook");
        System.out.println(addBook.execute());
    }

    @Test
    public void titleNull() throws ControllerException {
        UserHolder.setUser(new User("Goggi", "Goggi", AccessLevel.ADMIN));
        AddBook addBook = new AddBook(libraryDAO.getBookDAO());
        addBook.request(null, null);
        System.out.println(addBook.execute());
    }
}

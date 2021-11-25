package test.stepanovichalena.controller.actionlist;
import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.impl.actionlist.book.DeleteBook;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactory;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class DeleteBookTest {
    LibraryDAOFactory libraryDAO = LibraryDAOFactory.getInstance();

    @Test
    public void executeAdmin() {
        UserHolder.setUser(new User("Goggi", "Goggi", AccessLevel.ADMIN));
        DeleteBook deleteBook = new DeleteBook(libraryDAO.getBookDAO());
        deleteBook.request("TestAddBookAdmin", "TestAddBook");
        System.out.println(deleteBook.execute());
    }

    @Test
    public void executeUser() {
        UserHolder.setUser(new User("Kosm", "36987", AccessLevel.USER));
        DeleteBook deleteBook = new DeleteBook(libraryDAO.getBookDAO());
        deleteBook.request("TestAddBookUser", "TestAddBook");
        System.out.println(deleteBook.execute());
    }

    @Test
    public void executeNull () {
        UserHolder.setUser(new User("Goggi", "Goggi", AccessLevel.ADMIN));
        DeleteBook deleteBook = new DeleteBook(libraryDAO.getBookDAO());
        deleteBook.request(null, null);
        System.out.println(deleteBook.execute());
    }
}

package test.stepanovichalena.controller.actionlist.book;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.FindBookByAuthor;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import org.junit.Test;

public class FindBookByAuthorTest {
    LibraryDAOFactoryImpl libraryDAO = LibraryDAOFactoryImpl.getInstance();

    @Test
    public void FindBookByAuthorPositive() throws ControllerException {
        FindBookByAuthor findBook = new FindBookByAuthor(libraryDAO.getBookDAO());
        findBook.request( "J. R. R. Tolkien");
        System.out.println(findBook.execute());
    }
    @Test
    public void searchByTitleNull() throws ControllerException {
        FindBookByAuthor findBook = new FindBookByAuthor(libraryDAO.getBookDAO());
        findBook.request( null);
        System.out.println(findBook.execute());
    }
}

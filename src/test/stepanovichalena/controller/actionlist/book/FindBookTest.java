package test.stepanovichalena.controller.actionlist.book;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.FindBook;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import org.junit.Test;

public class FindBookTest {
    LibraryDAOFactoryImpl libraryDAO = LibraryDAOFactoryImpl.getInstance();

    @Test
    public void searchByTitlePositive() throws ControllerException {
        FindBook findBook = new FindBook(libraryDAO.getBookDAO());
        findBook.request("The Lord Of The Rings", "J. R. R. Tolkien");
        System.out.println(findBook.execute());
    }
    @Test
    public void searchByTitleNull() throws ControllerException {
        FindBook findBook = new FindBook(libraryDAO.getBookDAO());
        findBook.request(null, null);
        System.out.println(findBook.execute());
    }




}

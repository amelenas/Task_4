package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.FindBook;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindBookTest {

    @Test
    public void execute() throws ServiceException {
        FindBook findBook = new FindBook();
        assertEquals("Book found\n7/Jaws/Peter Benchley\n", findBook.execute("Jaws"));
    }
    @Test
    public void executeNotFound() throws ServiceException {
        FindBook findBook = new FindBook();
        assertEquals("Book not found", findBook.execute("Jaws2"));
    }
}
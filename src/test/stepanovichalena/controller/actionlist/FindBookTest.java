package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.FindBook;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindBookTest {

    @Test
    public void searchByTitlePositive() throws ServiceException {
        FindBook findBook = new FindBook();
        System.out.println(findBook.execute("Jaws/"));
    }

    @Test
    public void searchByTitleNegative() throws ServiceException {
        FindBook findBook = new FindBook();
        System.out.println(findBook.execute("Jaws2/"));
    }

    @Test
    public void searchByAuthorPositive() throws ServiceException {
        FindBook findBook = new FindBook();
        System.out.println(findBook.execute("/Peter Benchley"));
    }

    @Test
    public void searchByAuthorNegative() throws ServiceException {
        FindBook findBook = new FindBook();
        System.out.println(findBook.execute("/Peter Shmerchy"));
    }

    @Test
    public void searchByAuthorAndTitle() throws ServiceException {
        FindBook findBook = new FindBook();
        System.out.println(findBook.execute("Jaws/Peter Benchley"));
    }

    @Test
    public void searchNull() throws ServiceException {
        FindBook findBook = new FindBook();
        System.out.println(findBook.execute("null/null"));
    }

    @Test
    public void searchNull2() throws ServiceException {
        FindBook findBook = new FindBook();
        System.out.println(findBook.execute(null));
    }



}
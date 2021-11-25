package test.stepanovichalena.service;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactory;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactory;
import org.junit.Assert;
import org.junit.Test;

public class BookServiceImplTest {
    BookDAO bookDAO = LibraryDAOFactory.getInstance().getBookDAO();
    BookService bookService = ServiceLibraryFactory.getInstance().getBookService(bookDAO);

    @Test
    public void addBook() throws ServiceException {
        Assert.assertTrue(bookService.addBook(new Book(65, "BookServiceAddBook", "BookServiceAddBook")));
    }

    @Test
    public void addBookNull() throws ServiceException {
        Assert.assertFalse(bookService.addBook(null));
    }

    @Test
    public void find() throws ServiceException {
        System.out.println(bookService.find(new Book(0, "Jaws", "Peter Benchley")));
    }

    @Test
    public void findByAuthorNull() throws ServiceException {
        System.out.println(bookService.find(null));
    }

    @Test
    public void showAll() throws ServiceException {
        System.out.println(bookService.showAll());
    }

    @Test
    public void delete() throws ServiceException {
        Assert.assertTrue(bookService.delete(new Book(0, "BookServiceAddBook", "BookServiceAddBook")));
    }

    @Test
    public void deleteNull() throws ServiceException {
        Assert.assertFalse(bookService.delete(null));
    }
}

package test.stepanovichalena.service;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.factory.BookDAOFactory;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.UserHolder;
import by.stepanovichalena.library.service.factory.ServiceBookFactory;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.source.BookSource;
import by.stepanovichalena.library.source.impl.BookSourceImpl;
import org.junit.Test;

public class BookServiceImplTest {

    @Test
    public void addBookAdmin() throws ServiceException, LibraryDAOException {
        UserHolder.setUser(new User("Test2", "32165", AccessLevel.ADMIN));
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.addBook("Book/book"));
    }

    @Test
    public void addBookUser() throws ServiceException, LibraryDAOException {
        UserHolder.setUser(new User("Test", "123456", AccessLevel.USER));
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.addBook("Book/book"));
    }

    @Test
    public void addBookNull() throws ServiceException, LibraryDAOException {
        UserHolder.setUser(new User("Test2", "32165", AccessLevel.ADMIN));
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.addBook(null));
    }

    @Test
    public void findByTitle() throws LibraryDAOException {
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.find("Jaws/"));
    }

    @Test
    public void findByAuthor() throws LibraryDAOException {
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.find("/Peter Benchley"));
    }

    @Test
    public void findByAuthorNull() throws LibraryDAOException {
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.find(null));
    }

    @Test
    public void showAll() throws ServiceException, LibraryDAOException {
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.showAll());
    }

    @Test
    public void deleteAdmin() throws ServiceException, LibraryDAOException {
        UserHolder.setUser(new User("Test2", "32165", AccessLevel.ADMIN));
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.delete("Book/Book"));
    }

    @Test
    public void deleteAdminNull() throws ServiceException, LibraryDAOException {
        UserHolder.setUser(new User("Test2", "32165", AccessLevel.ADMIN));
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.delete(null));
    }

    @Test
    public void deleteUser() throws ServiceException, LibraryDAOException {
        UserHolder.setUser(new User("Test2", "32165", AccessLevel.USER));
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        System.out.println(bookService.delete("Book/Book"));
    }
}
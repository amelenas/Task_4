package test.stepanovichalena.service;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.logic.UserHolder;
import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.service.BookServiceImpl;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class BookServiceImplTest {

    @Test
    public void addBookAdmin() throws ServiceException {
        UserHolder.setUser(new User("Test2", "32165", AccessLevel.ADMIN));
        BookServiceImpl bookService = new BookServiceImpl();
        System.out.println(bookService.addBook("Book", "book"));
    }

    @Test
    public void addBookUser() throws ServiceException {
        UserHolder.setUser(new User("Test2", "32165", AccessLevel.USER));
        BookServiceImpl bookService = new BookServiceImpl();
        System.out.println(bookService.addBook("Book", "book"));
    }

    @Test
    public void findByTitle() throws ServiceException {
        BookServiceImpl bookService = new BookServiceImpl();
        System.out.println(bookService.find("Jaws", ""));
    }

    @Test
    public void findByAuthor() throws ServiceException {
        BookServiceImpl bookService = new BookServiceImpl();
        System.out.println(bookService.find("", "Peter Benchley"));
    }

    @Test
    public void showAll() throws ServiceException {
        BookServiceImpl bookService = new BookServiceImpl();
        bookService.showAll();
    }

    @Test
    public void deleteAdmin() throws ServiceException {
        UserHolder.setUser(new User("Test2", "32165", AccessLevel.ADMIN));
        BookServiceImpl bookService = new BookServiceImpl();
        System.out.println(bookService.delete("Book", "Book"));
    }

    @Test
    public void deleteUser() throws ServiceException {
        UserHolder.setUser(new User("Test2", "32165", AccessLevel.USER));
        BookServiceImpl bookService = new BookServiceImpl();
        System.out.println(bookService.delete("Book", "Book"));
    }
}
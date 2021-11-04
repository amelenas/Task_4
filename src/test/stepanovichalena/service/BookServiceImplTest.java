package test.stepanovichalena.service;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.logic.UserLogicImpl;
import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.service.BookServiceImpl;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    @Test
    public void addBookAdmin() throws ServiceException, LogicException {
        UserLogicImpl userLogic = new UserLogicImpl();
        userLogic.logIn(new User("Test2", "32165", AccessLevel.ADMIN));
        BookServiceImpl bookService = new BookServiceImpl();
        assertEquals("Request completed Book added", bookService.addBook("Book/book"));
    }

    @Test
    public void addBookUser() throws ServiceException, LogicException {
        UserLogicImpl userLogic = new UserLogicImpl();
        userLogic.logIn(new User("Lora", "368", AccessLevel.USER));
        BookServiceImpl bookService = new BookServiceImpl();
        assertEquals("Error while Book added", bookService.addBook("Book/book"));
    }

    @Test
    public void findByTitle() throws ServiceException {
        BookServiceImpl bookService = new BookServiceImpl();
        assertEquals("Book found\n7/Jaws/Peter Benchley\n", bookService.find("Jaws"));
    }

    @Test
    public void findByAuthor() throws ServiceException {
        BookServiceImpl bookService = new BookServiceImpl();
        assertEquals("Book found\n7/Jaws/Peter Benchley\n", bookService.find(" /Peter Benchley"));
    }

    @Test
    public void update() throws ServiceException, LogicException {
        UserLogicImpl userLogic = new UserLogicImpl();
        userLogic.logIn(new User("Test2", "32165", AccessLevel.ADMIN));
        BookServiceImpl bookService = new BookServiceImpl();
        bookService.update("19/Catch-22/Joseph Heller/");
    }

    @Test
    public void delete() throws ServiceException {
        BookServiceImpl bookService = new BookServiceImpl();
        bookService.delete("31/TestUpdate/TestUpdate/");
    }
}
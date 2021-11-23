package test.stepanovichalena.logic;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.factory.BookDAOFactory;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.source.BookSource;
import by.stepanovichalena.library.source.impl.BookSourceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookDAOImplTest {

    @Test
    public void create() throws LibraryDAOException {
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);

        assertTrue(bookDAO.addBook(new Book(0, "TestCreate", "TestCreate")));
    }

    @Test
    public void delete() throws LibraryDAOException {
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);

        assertTrue(bookDAO.delete(new Book(27, "TestCreate", "TestCreate")));
    }

    @Test
    public void read() throws LibraryDAOException {
        Book expected = new Book(13, "Mary Poppins", "Pamela Lyndon Travers");
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        bookDAO.connectBookSource(bookSource);
        assertTrue(bookDAO.find(new Book(13, "Mary Poppins", "Pamela Lyndon Travers")).contains(expected));
    }
}
package test.stepanovichalena.dao;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactory;
import by.stepanovichalena.library.entity.Book;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BookDAOImplTest {
    LibraryDAOFactory libraryDAOFactory = LibraryDAOFactory.getInstance();
    BookDAO bookDAO = libraryDAOFactory.getBookDAO();

    @Test
    public void create() throws LibraryDAOException {
        assertTrue(bookDAO.create(new Book(0, "TestCreate", "TestCreate")));
    }

    @Test
    public void delete() throws LibraryDAOException {
        assertTrue(bookDAO.delete(new Book(27, "TestCreate", "TestCreate")));
    }

    @Test
    public void read() throws LibraryDAOException {
        System.out.println(bookDAO.find(new Book(13, "Mary Poppins", "Pamela Lyndon Travers")));
    }
}
package test.stepanovichalena.dao;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import by.stepanovichalena.library.entity.Book;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookDAOImplTest {
    LibraryDAOFactoryImpl libraryDAOFactory = LibraryDAOFactoryImpl.getInstance();
    BookDAO bookDAO = libraryDAOFactory.getBookDAO();

    @Test
    public void create() throws LibraryDAOException {
        assertTrue(bookDAO.create(new Book(0, "TestCreate", "TestCreate")));
    }

    @Test (expected = LibraryDAOException.class)
    public void createNull() throws LibraryDAOException {
        assertFalse(bookDAO.create(null));
    }

    @Test
    public void delete() throws LibraryDAOException {
        assertTrue(bookDAO.delete(new Book(27, "TestCreate", "TestCreate")));
    }

    @Test
    public void deleteNull() throws LibraryDAOException {
        assertFalse(bookDAO.delete(new Book(27, "TestCreate", "TestCreate")));
    }

    @Test
    public void deleteById() throws LibraryDAOException {
        assertTrue(bookDAO.deleteById(3));
    }

    @Test
    public void deleteByIdNull() throws LibraryDAOException {
        assertFalse(bookDAO.deleteById(null));
    }

    @Test
    public void read() throws LibraryDAOException {
        System.out.println(bookDAO.find(new Book(13, "Mary Poppins", "Pamela Lyndon Travers")));
    }

    @Test (expected = LibraryDAOException.class)
    public void readNull() throws LibraryDAOException {
        System.out.println(bookDAO.find(null));
    }

    @Test
    public void findByAuthorBook() throws LibraryDAOException {
        System.out.println(bookDAO.findByAuthorBook("Pamela Lyndon Travers"));
    }

    @Test
    public void findByAuthorBookNull() throws LibraryDAOException {
        System.out.println(bookDAO.findByAuthorBook(null));
    }

    @Test
    public void findByTitle() throws LibraryDAOException {
        System.out.println(bookDAO.findByTitle("Mary Poppins"));
    }

    @Test
    public void findByTitleNull() throws LibraryDAOException {
        System.out.println(bookDAO.findByTitle(null));
    }
}
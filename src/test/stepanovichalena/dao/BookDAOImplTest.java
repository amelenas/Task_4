package test.stepanovichalena.dao;

import by.stepanovichalena.library.dao.BookDAOImpl;
import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.entity.Book;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BookDAOImplTest {

    @Test
    public void readAll() throws DAOException {
        Map<Integer, Book> map = BookDAOImpl.getInstance().readAll();
        System.out.println(map);
    }

    @Test
    public void writeAll() throws DAOException {
        Set<Book> map = new HashSet<>();
        Book book = new Book(31, "Test", "Test");
        map.add(book);
        BookDAOImpl.getInstance().writeAll(map);
        System.out.println(map);
    }
}
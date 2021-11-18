package test.stepanovichalena.dao;

import by.stepanovichalena.library.dao.BookDAOImpl;
import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.entity.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BookDAOImplTest {

    @Before
    public void readAll() throws DAOException {
        Map<Integer, Book> map = BookDAOImpl.getInstance().readAll();
        System.out.println(map);
    }

    @Test
    public void writeAll() throws DAOException {
        Map<Integer, Book> map = BookDAOImpl.getInstance().readAll();
        int key = map.size()+1;
        Book book = new Book(key, "Test", "Test");
        map.put(key, book);
        Set<Book> allValues = new TreeSet<>(map.values());
        BookDAOImpl.getInstance().writeAll(allValues);
        System.out.println(map);
    }
}
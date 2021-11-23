package test.stepanovichalena.dao;

import by.stepanovichalena.library.source.impl.BookSourceImpl;
import by.stepanovichalena.library.source.exception.DAOException;
import by.stepanovichalena.library.entity.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BookSourceImplTest {

    @Before
    public void readAll() throws DAOException {
        Map<Integer, Book> map = new BookSourceImpl().readAll();
        System.out.println(map);
    }

    @Test
    public void writeAll() throws DAOException {
        Map<Integer, Book> map = new BookSourceImpl().readAll();
        int key = map.size()+1;
        Book book = new Book(key, "Test", "Test");
        map.put(key, book);
        Set<Book> allValues = new TreeSet<>(map.values());
        new BookSourceImpl().writeAll(allValues);
        System.out.println(map);
    }
}
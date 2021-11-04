package test.stepanovichalena.logic;

import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.logic.BookLogicImpl;
import by.stepanovichalena.library.logic.exception.LogicException;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookLogicImplTest {
    @Test
    public void create() throws LogicException {
        BookLogicImpl bookLogic = new BookLogicImpl();
        assertTrue(bookLogic.create(new Book(30, "TestCreate", "TestCreate")));
    }

    @Test
    public void delete() throws LogicException {
        BookLogicImpl bookLogic = new BookLogicImpl();
        assertTrue(bookLogic.delete(11));
    }

    @Test
    public void update() throws LogicException {
        BookLogicImpl bookLogic = new BookLogicImpl();
        assertTrue(bookLogic.update(new Book(32, "TestUpdate", "TestUpdate")));
    }

    @Test
    public void read() throws LogicException {
       Book expected = new Book(13, "Mary Poppins", "Pamela Lyndon Travers");
        BookLogicImpl bookLogic = new BookLogicImpl();
        assertTrue(bookLogic.find(new Book(13, "Mary Poppins", "Pamela Lyndon Travers")).contains(expected));
    }
}
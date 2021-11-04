package test.stepanovichalena.service.validation;

import by.stepanovichalena.library.service.validation.BookValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookValidatorTest {

    @Test
    public void validateRead() {
        BookValidator bookValidator = BookValidator.getInstance();
        System.out.println(bookValidator.validateSearch("Book/Book"));
    }

    @Test
    public void validateCreate() {
        BookValidator bookValidator = BookValidator.getInstance();
        System.out.println(bookValidator.validateCreate("Book12/Book12"));
    }

    @Test
    public void validateUpdate() {
        BookValidator bookValidator = BookValidator.getInstance();
        System.out.println(bookValidator.validateUpdate("18/Book/Book"));
    }

    @Test
    public void validateDelete() {
        BookValidator bookValidator = BookValidator.getInstance();
        assertEquals(22, bookValidator.validateDelete("22"));
    }
}
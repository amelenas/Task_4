package test.stepanovichalena.controller.actionlist.validation;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.impl.BookValidator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookValidatorTest {
    BookValidator bookValidator = new BookValidator();

    @Test
    public void validateNameAndTitle() throws ControllerException {
        Assert.assertTrue(bookValidator.isBookDataValid("Author", "Book"));
    }

    @Test
    public void validateNameAndTitleNegative() throws ControllerException {
        assertFalse(bookValidator.isBookDataValid("Book", "Author32"));
    }

    @Test
    public void validateNamePositive() throws ControllerException {
        assertTrue(bookValidator.isAuthorsNameValid("Author"));
    }

    @Test
    public void validateTitlePositive() throws ControllerException {
        assertTrue(bookValidator.isTitleValid("C++"));
    }

    @Test
    public void validateNameLastName() throws ControllerException {
        assertTrue(bookValidator.isAuthorsNameValid("Author Author"));
    }

    @Test
    public void validateNameNegative() throws ControllerException {
        assertFalse(bookValidator.isAuthorsNameValid("Author32"));
    }
    @Test
    public void isIdValidPositive() throws ControllerException {
        assertTrue(bookValidator.isIdValid("32"));
    }
    @Test
    public void isIdValidNegative() throws ControllerException {
        assertFalse(bookValidator.isIdValid("LLLL"));
    }
    @Test
    public void isIdValidNumbersAndLetters() throws ControllerException {
        assertFalse(bookValidator.isIdValid("32LLLL"));
    }
    @Test
    public void isIdValidNumbersAndLetters2() throws ControllerException {
        assertFalse(bookValidator.isIdValid("32 LLLL"));
    }
}

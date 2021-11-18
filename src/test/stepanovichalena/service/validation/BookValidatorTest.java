package test.stepanovichalena.service.validation;

import by.stepanovichalena.library.service.validation.BookValidator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookValidatorTest {
    BookValidator bookValidator = new BookValidator();

    @Test
    public void validateNameAndTitle() {
        Assert.assertTrue(bookValidator.isBookDataValid("Author", "Book"));
    }

    @Test
    public void validateNameAndTitleNegative() {
        assertFalse(bookValidator.isBookDataValid("Author32", "Book"));
    }

    @Test
    public void validateNamePositive() {
        assertTrue(bookValidator.isAuthorsNameValid("Author"));
    }

    @Test
    public void validateTitlePositive() {
        assertTrue(bookValidator.isTitleValid("C++"));
    }

    @Test
    public void validateNameLastName() {
        assertTrue(bookValidator.isAuthorsNameValid("Author Author"));
    }

    @Test
    public void validateNameNegative() {
        assertFalse(bookValidator.isAuthorsNameValid("Author32"));
    }

}
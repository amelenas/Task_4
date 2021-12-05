package test.stepanovichalena.validation;

import by.stepanovichalena.library.validation.impl.BookValidator;
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
        assertFalse(bookValidator.isBookDataValid("Book", "Author32"));
    }

    @Test
    public void validateNamePositive() {
        assertTrue(bookValidator.isAuthorsNameValid("Author"));
    }

    @Test
    public void validateTitlePositive(){
        assertTrue(bookValidator.isTitleValid("C++"));
    }

    @Test
    public void validateNameLastName()  {
        assertTrue(bookValidator.isAuthorsNameValid("Author Author"));
    }

    @Test
    public void validateNameNegative() {
        assertFalse(bookValidator.isAuthorsNameValid("Author32"));
    }
    @Test
    public void isIdValidPositive() {
        assertTrue(bookValidator.isIdValid("32"));
    }
    @Test
    public void isIdValidNegative() {
        assertFalse(bookValidator.isIdValid("LLLL"));
    }
    @Test
    public void isIdValidNumbersAndLetters()  {
        assertFalse(bookValidator.isIdValid("32LLLL"));
    }
    @Test
    public void isIdValidNumbersAndLetters2()  {
        assertFalse(bookValidator.isIdValid("32 LLLL"));
    }
}

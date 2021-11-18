package test.stepanovichalena.service.validation;

import by.stepanovichalena.library.service.validation.UserValidator;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserValidatorTest {
    UserValidator userValidator = new UserValidator();
    @Test
    public void validateUser() {
        assertTrue(userValidator.isUserDataValid("UserName", "UserName"));
    }
    @Test
    public void badUserName() {
        assertFalse(userValidator.isUserDataValid("User Name", "UserName"));
    }
    @Test
    public void badPassword() {
        assertFalse(userValidator.isUserDataValid("UserName", "Use"));
    }
}
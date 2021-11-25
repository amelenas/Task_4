package test.stepanovichalena.service.validation;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.impl.UserValidator;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserValidatorTest {
    UserValidator userValidator = new UserValidator();
    @Test
    public void validateUser() throws ControllerException {
        assertTrue(userValidator.isUserDataValid("UserName", "UserName"));
    }
    @Test
    public void badUserName() throws ControllerException {
        assertFalse(userValidator.isUserDataValid("User Name", "UserName"));
    }
    @Test
    public void badPassword() throws ControllerException {
        assertFalse(userValidator.isUserDataValid("UserName", "Use"));
    }
}

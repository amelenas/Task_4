package test.stepanovichalena.service.validation;

import by.stepanovichalena.library.service.validation.UserValidator;
import org.junit.Test;

public class UserValidatorTest {

    @Test
    public void validateUser() {
        UserValidator userValidator = UserValidator.getInstance();
        System.out.println(userValidator.validateUser("ValidationTest ValidationTest"));
    }
}
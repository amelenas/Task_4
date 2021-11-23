package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.SignUp;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class SignUpTest {

    @Test
    public void executeUser() throws ServiceException {
        SignUp signUp = new SignUp();
        System.out.println(signUp.execute("SignInTest/SignInTest"));
   }

    @Test
    public void executeNull() throws ServiceException {
        SignUp signUp = new SignUp();
        System.out.println(signUp.execute(null));
    }
}
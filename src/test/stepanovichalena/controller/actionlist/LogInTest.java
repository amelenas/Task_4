package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.LogIn;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class LogInTest {

    @Test
    public void execute() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("SignInTest SignInTest USER"));
    }
    @Test
    public void executeWrongPassword() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("SignInTest SignIn USER"));
    }
}
package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.LogIn;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class LogInTest {

    @Test
    public void execute() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("SignInTest/SignInTest"));
    }
    @Test
    public void executeWrongPassword() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("SignInTest/SignIn"));
    }
    @Test
    public void executeNull() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("null/null"));
    }

    @Test
    public void executeNull2() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute(null));
    }
}
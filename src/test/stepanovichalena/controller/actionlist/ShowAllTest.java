package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.LogIn;
import by.stepanovichalena.library.controller.actionlist.ShowAll;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class ShowAllTest {

    @Test
    public void executeAdmin() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("Kosm/36987"));
        ShowAll showAllTest = new ShowAll();
        System.out.println(showAllTest.execute("/"));
    }

    @Test
    public void executeUser() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("SignInTest/SignInTest"));
        ShowAll showAllTest = new ShowAll();
        System.out.println(showAllTest.execute("/"));
    }

    @Test
    public void executeNull() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("SignInTest/SignInTest"));
        ShowAll showAllTest = new ShowAll();
        System.out.println(showAllTest.execute(null));
    }
}
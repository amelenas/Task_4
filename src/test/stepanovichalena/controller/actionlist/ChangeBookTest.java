package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.ChangeBook;
import by.stepanovichalena.library.controller.actionlist.LogIn;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class ChangeBookTest {

    @Test
    public void executeAdmin() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("Kosm 36987"));
        ChangeBook changeBook = new ChangeBook();
        System.out.println(changeBook.execute("13/Alice in Wonderland/Lewis Carroll"));
    }

    @Test
    public void executeUser() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("Lora 368"));
        ChangeBook changeBook = new ChangeBook();
        System.out.println(changeBook.execute("13/Alice in Wonderland/Lewis Carroll"));
    }
}
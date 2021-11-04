package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.DeleteBook;
import by.stepanovichalena.library.controller.actionlist.LogIn;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class DeleteBookTest {

    @Test
    public void executeAdmin() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("Kosm 36987"));
        DeleteBook deleteBook = new DeleteBook();
        System.out.println(deleteBook.execute("11"));
    }

    @Test
    public void executeUser() throws ServiceException{
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("Lora 368"));
        DeleteBook deleteBook = new DeleteBook();
        System.out.println(deleteBook.execute("11"));
    }
}
package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.AddBook;
import by.stepanovichalena.library.controller.actionlist.LogIn;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class AddBookTest {

    @Test
    public void executeAdmin() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("Kosm 36987"));
        AddBook addBook = new AddBook();
        System.out.println(addBook.execute("Alice in Wonderland/Lewis Carroll"));
    }
    @Test
    public void executeUser() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("Lora 368"));
        AddBook addBook = new AddBook();
        System.out.println(addBook.execute("Alice in Wonderland/Lewis Carroll"));
    }

}
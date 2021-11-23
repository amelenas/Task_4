package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.AddBook;
import by.stepanovichalena.library.controller.actionlist.LogIn;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class AddBookTest {

    @Test
    public void executeAdmin() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("Kosm/36987"));
        AddBook addBook = new AddBook();
        System.out.println(addBook.execute("Alice in Wonderland/Lewis Carroll"));
    }

    @Test
    public void executeUser() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("Goggi/Goggi"));
        AddBook addBook = new AddBook();
        System.out.println(addBook.execute("Alice in Wonderland/Lewis Carroll"));
    }

    @Test
    public void titleNumber() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("UserName/UserName"));
        AddBook addBook = new AddBook();
        System.out.println(addBook.execute("9999/Lewis C"));
    }

    @Test
    public void titleNull() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("UserName/UserName"));
        AddBook addBook = new AddBook();
        System.out.println(addBook.execute("null/null"));
    }

    @Test
    public void titleNull2() throws ServiceException {
        LogIn logIn = new LogIn();
        System.out.println(logIn.execute("UserName/UserName"));
        AddBook addBook = new AddBook();
        System.out.println(addBook.execute(null));
    }
}
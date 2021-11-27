package test.stepanovichalena.controller.actionlist.book;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.ShowAll;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import org.junit.Test;

public class ShowAllTest {
    LibraryDAOFactoryImpl libraryDAO = LibraryDAOFactoryImpl.getInstance();

    @Test
    public void executePositive() throws ControllerException {
        ShowAll showAllTest = new ShowAll(libraryDAO.getBookDAO());
        showAllTest.request("", "");
       System.out.println(showAllTest.execute());
    }

    @Test
    public void executeUser() throws ControllerException {
        ShowAll showAllTest = new ShowAll(libraryDAO.getBookDAO());
        showAllTest.request(null);
        System.out.println(showAllTest.execute());
    }
}
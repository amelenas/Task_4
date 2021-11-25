package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.ShowAll;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactory;
import org.junit.Test;

public class ShowAllTest {
    LibraryDAOFactory libraryDAO = LibraryDAOFactory.getInstance();

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
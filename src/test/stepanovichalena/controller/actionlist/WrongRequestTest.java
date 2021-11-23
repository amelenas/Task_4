package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.WrongRequest;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class WrongRequestTest {

    @Test
    public void execute() throws ServiceException {
        WrongRequest errorRequest = new WrongRequest();
        System.out.println(errorRequest.execute("Error request/"));
    }

    @Test
    public void executeNull() throws ServiceException {
        WrongRequest errorRequest = new WrongRequest();
        System.out.println(errorRequest.execute(null));
    }
}
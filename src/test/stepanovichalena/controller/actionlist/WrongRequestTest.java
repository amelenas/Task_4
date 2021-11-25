package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.WrongRequest;
import org.junit.Test;

public class WrongRequestTest {

    @Test
    public void execute() throws ControllerException {
        WrongRequest errorRequest = new WrongRequest();
        errorRequest.request("bebebebe");
        System.out.println(errorRequest.execute());
    }

    @Test
    public void executeNull() throws ControllerException {
        WrongRequest errorRequest = new WrongRequest();
        errorRequest.request("bebebebe");
        System.out.println(errorRequest.execute());
    }
}

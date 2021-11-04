package test.stepanovichalena.controller.actionlist;

import by.stepanovichalena.library.controller.actionlist.ErrorRequest;
import org.junit.Assert;
import org.junit.Test;

public class ErrorRequestTest {

    @Test
    public void execute() {
        ErrorRequest errorRequest = new ErrorRequest();
        Assert.assertEquals("Wrong request", errorRequest.execute("Error request"));
    }
}
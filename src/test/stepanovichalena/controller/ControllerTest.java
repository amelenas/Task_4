package test.stepanovichalena.controller;

import by.stepanovichalena.library.controller.Controller;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {

    @Test
    public void start() throws ServiceException {
        Controller controller = new Controller();
        assertEquals("Book found\n21/The Lord Of The Rings/J. R. R. Tolkien\n", controller.start("FIND_BOOK The Lord Of The Rings/J. R. R. Tolkien"));
    }
}
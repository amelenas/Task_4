package by.stepanovichalena.library.controller;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.util.Command;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public String start(Actions actions, String request) throws ControllerException {
        String response;
        Commands commands = new Commands();
        Command command = commands.getCommand(actions);
        try {
            response = command.execute(request);
        } catch (ServiceException e) {
            LOGGER.warn(e.getMessage());
            throw new ControllerException("Exception in Controller while command execute", e);
        }
        return response;
    }
}


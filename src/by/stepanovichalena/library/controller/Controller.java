package by.stepanovichalena.library.controller;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.util.Command;
import by.stepanovichalena.library.service.exception.ServiceException;

public class Controller {

    public String start(Actions actions, String firstRequest, String secondRequest) throws ControllerException {
        String response;
        Commands commands = new Commands();
        Command command = commands.getCommand(actions);
        try {
            response = command.execute(firstRequest, secondRequest);
        } catch (ServiceException e) {
            throw new ControllerException("Exception in Controller while command execute", e);
        }
        return response;
    }
}


package by.stepanovichalena.library.controller;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.util.Command;
import by.stepanovichalena.library.service.exception.ServiceException;

public class Controller {
    private String commandName;
    private String actualRequest;

    public String start(String request) throws ControllerException {
        requestSplit(request);
        String response;
        Commands commands = new Commands();
        Command command = commands.getCommand(commandName);
        try {
            response = command.execute(actualRequest);
        } catch (ServiceException e) {
            throw new ControllerException("Exception in Controller while command execute", e);
        }
        return response;
    }

   private void requestSplit(String str) {
        if (str == null) {
            commandName = null;
            actualRequest = null;
        } else {
            String[] array = str.split(" ");
            commandName = array[0];
            actualRequest = str.replace(commandName, "").trim();
        }
    }
}


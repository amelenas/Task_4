package by.stepanovichalena.library.controller;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.Commands;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    private List<Command> commandsList;

       private static Commands commands;


    public Controller(){}

    public Controller(Commands commands) {
        this.commands = commands;
        {
            commandsList = new ArrayList<>();
            commandsList.add(commands.getCommand(Actions.SIGN_UP));
            commandsList.add(commands.getCommand(Actions.LOG_IN));
            commandsList.add(commands.getCommand(Actions.ADD_BOOK));
            commandsList.add(commands.getCommand(Actions.FIND_BOOK));
            commandsList.add(commands.getCommand(Actions.DELETE_BOOK));
            commandsList.add(commands.getCommand(Actions.SHOW_ALL));
            commandsList.add(commands.getCommand(Actions.WRONG_REQUEST));
        }
    }

    public static void setCommands(Commands commands) {
        Controller.commands = commands;
    }

    public String start(Actions actions, String ... request) throws ControllerException {
        String response;
        Command command = commands.getCommand(actions);
        command.request(request);
        try {
            response = command.execute();
        } catch (ControllerException e) {
            LOGGER.warn(e.getMessage());
            throw new ControllerException("Exception in Controller while command execute", e);
        }
        return response;
    }
}

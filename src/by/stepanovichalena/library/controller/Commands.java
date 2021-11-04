package by.stepanovichalena.library.controller;

import by.stepanovichalena.library.controller.actionlist.*;
import by.stepanovichalena.library.controller.util.Command;

import java.util.HashMap;
import java.util.Map;

public class Commands {

    private static final Map<Actions, Command> commands = new HashMap<>();

    public Commands() {
        commands.put(Actions.SIGN_UP, new SignUp());
        commands.put(Actions.LOG_IN, new LogIn());
        commands.put(Actions.ADD_BOOK, new AddBook());
        commands.put(Actions.FIND_BOOK, new FindBook());
        commands.put(Actions.DELETE_BOOK, new DeleteBook());
        commands.put(Actions.CHANGE_BOOK, new ChangeBook());
        commands.put(Actions.WRONG_REQUEST, new ErrorRequest());
    }

    public Command getCommand(String name){
        Actions actions = Actions.parse(name);
        Command command;
        if (actions == null) {
            command = commands.get(Actions.WRONG_REQUEST);
        } else {
            command = commands.get(actions);
        }
        return command; }


}

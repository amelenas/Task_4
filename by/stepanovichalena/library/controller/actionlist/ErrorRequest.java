package by.stepanovichalena.library.controller.actionlist;

import by.stepanovichalena.library.controller.util.Command;

public class ErrorRequest implements Command {
    @Override
    public String execute(String request) {
        return "wrong request";
    }
}


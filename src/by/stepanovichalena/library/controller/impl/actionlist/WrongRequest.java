package by.stepanovichalena.library.controller.impl.actionlist;

import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.exception.ControllerException;

import java.util.Arrays;

public class WrongRequest implements Command {
    private String[] requestParameters;

    @Override
    public String execute() throws ControllerException {
        return Arrays.toString(requestParameters) + " Wrong request";
    }

    @Override
    public void request(String... requestParameters) {
        this.requestParameters = requestParameters;
    }
}


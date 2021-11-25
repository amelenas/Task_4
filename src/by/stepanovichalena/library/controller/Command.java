package by.stepanovichalena.library.controller;

import by.stepanovichalena.library.controller.exception.ControllerException;

public interface Command {

    String execute() throws ControllerException;

    void request(String... requestParameters);


}

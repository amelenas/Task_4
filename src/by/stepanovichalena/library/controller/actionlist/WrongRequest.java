package by.stepanovichalena.library.controller.actionlist;

import by.stepanovichalena.library.controller.util.Command;
import by.stepanovichalena.library.service.exception.ServiceException;

public class WrongRequest implements Command {

    @Override
    public String execute(String firstRequest, String secondRequest) throws ServiceException {
        return "Wrong request";
    }
}


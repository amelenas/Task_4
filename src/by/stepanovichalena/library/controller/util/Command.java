package by.stepanovichalena.library.controller.util;

import by.stepanovichalena.library.service.exception.ServiceException;

public interface Command {

    String execute(String request) throws ServiceException;

}

package by.stepanovichalena.library.controller.util;

import by.stepanovichalena.library.service.exception.ServiceException;

public interface Command {

    String execute(String firstRequest, String secondRequest) throws ServiceException;

}

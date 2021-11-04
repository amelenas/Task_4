package by.stepanovichalena.library.service.util;

import by.stepanovichalena.library.service.exception.ServiceException;

public interface UserService {
    String register(String request) throws ServiceException;

    String logIn(String request);
}

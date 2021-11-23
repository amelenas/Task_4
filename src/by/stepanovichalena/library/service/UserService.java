package by.stepanovichalena.library.service;

import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.validation.util.UserValidation;

public interface UserService {

    String register(String request) throws ServiceException;

    String logIn(String request) throws ServiceException;

    void connectUserDAO(UserDAO userDAO);

    void setUserValidation(UserValidation userValidation);
}

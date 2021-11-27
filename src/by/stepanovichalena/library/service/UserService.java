package by.stepanovichalena.library.service;

import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.exception.ServiceException;

public interface UserService {

    boolean register(User user) throws ServiceException;

    boolean logIn(User user) throws ServiceException;

    boolean findUser(User user) throws ServiceException;

    boolean isAccessLevelAdmin(User user);

    boolean changeAccessLevel(User user) throws ServiceException;
}

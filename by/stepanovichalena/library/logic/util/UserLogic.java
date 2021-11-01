package by.stepanovichalena.library.logic.util;

import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.logic.exception.LogicException;

public interface UserLogic {
    boolean register(User user) throws LogicException;

    boolean logIn(User user);
}

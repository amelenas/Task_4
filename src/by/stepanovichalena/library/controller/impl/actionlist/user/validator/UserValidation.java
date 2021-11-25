package by.stepanovichalena.library.controller.impl.actionlist.user.validator;

import by.stepanovichalena.library.controller.exception.ControllerException;

public interface UserValidation {

    boolean isUserDataValid(String UserName, String password) throws ControllerException;

}

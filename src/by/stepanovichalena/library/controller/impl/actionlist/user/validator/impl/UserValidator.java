package by.stepanovichalena.library.controller.impl.actionlist.user.validator.impl;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.user.validator.UserValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements UserValidation {
    private final static String COMMON_PATTERN = "([\\w_])";
    private final static String PASSWORD_PATTERN = COMMON_PATTERN + "{5,20}";
    private final static String NAME_PATTERN = COMMON_PATTERN + "{2,30}";


    @Override
    public boolean isUserDataValid(String userName, String password) throws ControllerException {
         if (userName == null|| password == null) {
           throw new ControllerException("User name or password is null");
        }
          return isDataValid(NAME_PATTERN, userName) && isDataValid(PASSWORD_PATTERN, password);
    }

    @Override
    public boolean isUserNameValid(String userName) throws ControllerException {
        if (userName == null) {
            throw new ControllerException("User name is null");
        }
        return isDataValid(NAME_PATTERN, userName);

    }

    private boolean isDataValid(String pattern, String arg) {
        Pattern patternCompile = Pattern.compile(pattern);
        Matcher matcher = patternCompile.matcher(arg);
        return matcher.matches();
    }
}

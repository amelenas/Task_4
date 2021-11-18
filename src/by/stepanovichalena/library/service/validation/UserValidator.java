package by.stepanovichalena.library.service.validation;

import by.stepanovichalena.library.service.validation.util.UserValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements UserValidation {
    private final static String COMMON_PATTERN = "([\\w_])";
    private final static String PASSWORD_PATTERN = COMMON_PATTERN + "{5,20}";
    private final static String NAME_PATTERN = COMMON_PATTERN + "{2,30}";


    @Override
    public boolean isUserDataValid(String userName, String password) {
         if (userName == null|| password == null) {
            return false;
        }
          return isDataValid(NAME_PATTERN, userName) && isDataValid(PASSWORD_PATTERN, password);
    }

    private boolean isDataValid(String pattern, String arg) {
        Pattern patternCompile = Pattern.compile(pattern);
        Matcher matcher = patternCompile.matcher(arg);
        return matcher.matches();
    }
}

package by.stepanovichalena.library.service.validation;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.service.validation.util.UserValidation;

public class UserValidator implements UserValidation {
    private final static String DIVIDER = " ";

    private UserValidator() {
    }

    private static class UserValidatorHolder {
        public static final UserValidator HOLDER_INSTANCE = new UserValidator();
    }

    public static UserValidator getInstance() {
        return UserValidatorHolder.HOLDER_INSTANCE;
    }

    @Override
    public User validateUser(String user) {
         if (user == null) {
            return null;
        }
        String[] array = user.split(DIVIDER);
        if (array.length == 2) {
            return new User(array[0], array[1], AccessLevel.DEFAULT);}
        if (array.length == 3) {
            return new User(array[0], array[1], AccessLevel.valueOf(array[2]));}
        else {
            return new User(array[0], "", AccessLevel.DEFAULT);
        }
    }
}

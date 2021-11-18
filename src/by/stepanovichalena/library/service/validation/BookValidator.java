package by.stepanovichalena.library.service.validation;

import by.stepanovichalena.library.service.validation.util.BookValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BookValidator implements BookValidation {
    private final static String NAME_PATTERN = "^[a-zA-Z'][a-zA-Z-' ]+[a-zA-Z']?$";
    private final static String TITLE_PATTERN = "(.{1,30})";

    @Override
    public boolean isTitleValid(String title) {
        if (isEmpty(title)) {
            return false;
        }
        return isDataValid(TITLE_PATTERN, title);
    }

    @Override
    public boolean isAuthorsNameValid(String authorsName) {
        if (isEmpty(authorsName)) {
            return false;
        }
        return isDataValid(NAME_PATTERN, authorsName);
    }

    @Override
    public boolean isBookDataValid(String title, String authorsName) {
        if (isEmpty(authorsName) && isEmpty(title)) {
            return false;
        }
        return isDataValid(NAME_PATTERN, authorsName) && isDataValid(TITLE_PATTERN, title);
    }

    private static boolean isEmpty(String verifiable) {
        return (verifiable == null || verifiable.equals(""));
    }

    private boolean isDataValid(String pattern, String arg) {
        Pattern patternCompile = Pattern.compile(pattern);
        Matcher matcher = patternCompile.matcher(arg);
        return matcher.matches();
    }
}

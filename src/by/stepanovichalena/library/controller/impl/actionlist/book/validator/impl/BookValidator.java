package by.stepanovichalena.library.controller.impl.actionlist.book.validator.impl;


import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.BookValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BookValidator implements BookValidation {
    private final static String NAME_PATTERN = "^[a-zA-Z'][a-zA-Z-' ]+[a-zA-Z']?$";
    private final static String TITLE_PATTERN = "(.{1,30})";

    @Override
    public boolean isTitleValid(String title) throws ControllerException {
        if (isEmpty(title)) {
            throw new ControllerException("The data is empty");
        }
        return isDataValid(TITLE_PATTERN, title);
    }

    @Override
    public boolean isAuthorsNameValid(String authorsName) throws ControllerException {
        if (isEmpty(authorsName)) {
            throw new ControllerException("The data is empty");
        }
        return isDataValid(NAME_PATTERN, authorsName);
    }

    @Override
    public boolean isBookDataValid(String title, String authorsName) throws ControllerException {
        if (isEmpty(authorsName) && isEmpty(title)) {
            throw new ControllerException("The data is empty");
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

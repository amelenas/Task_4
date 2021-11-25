package by.stepanovichalena.library.controller.impl.actionlist.book.validator;

import by.stepanovichalena.library.controller.exception.ControllerException;

public interface BookValidation {

    boolean isBookDataValid(String authorsName, String title) throws ControllerException;

    boolean isAuthorsNameValid(String authorsName) throws ControllerException;

    boolean isTitleValid(String title) throws ControllerException;
}

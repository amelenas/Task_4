package by.stepanovichalena.library.service.validation.util;

public interface BookValidation {

    boolean isBookDataValid(String authorsName, String title);

    boolean isAuthorsNameValid(String authorsName);

    boolean isTitleValid(String title);
}

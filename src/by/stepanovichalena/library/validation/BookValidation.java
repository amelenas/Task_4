package by.stepanovichalena.library.validation;

public interface BookValidation {

    boolean isBookDataValid(String authorsName, String title);

    boolean isAuthorsNameValid(String authorsName);

    boolean isIdValid(String id);

    boolean isTitleValid(String title);
}

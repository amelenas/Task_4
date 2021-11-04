package by.stepanovichalena.library.logic;

import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.logic.util.BookLogic;
import by.stepanovichalena.library.logic.util.UserLogic;

public class LibraryFactory {
    private static LibraryFactory INSTANCE;
    private BookLogic bookLogic;
    private UserLogic userLogic;

    private LibraryFactory() throws LogicException {
        bookLogic = new BookLogicImpl();
        userLogic = new UserLogicImpl();
    }

    public static LibraryFactory getInstance() throws LogicException {
        synchronized (LibraryFactory.class) {
            if (INSTANCE == null) {
                INSTANCE = new LibraryFactory();
            }
        }
        return INSTANCE;
    }

    public BookLogic getBookDAO() {
        return bookLogic;
    }

    public UserLogic getUserDAO() {
        return userLogic;
    }
}

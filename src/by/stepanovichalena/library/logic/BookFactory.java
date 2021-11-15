package by.stepanovichalena.library.logic;

import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.logic.util.BookLogic;
import by.stepanovichalena.library.logic.util.UserLogic;

public class BookFactory {
    private static BookFactory INSTANCE;
    private BookLogic bookLogic;
    private UserLogic userLogic;

    private BookFactory() throws LogicException {
        bookLogic = new BookLogicImpl();
        userLogic = new UserLogicImpl();
    }

    public static BookFactory getInstance() throws LogicException {
        synchronized (BookFactory.class) {
            if (INSTANCE == null) {
                INSTANCE = new BookFactory();
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

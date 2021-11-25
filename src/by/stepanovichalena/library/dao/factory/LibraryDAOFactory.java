package by.stepanovichalena.library.dao.factory;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.UserDAO;

public interface LibraryDAOFactory {

    LibraryDAOFactory INSTANCE = null;

    static LibraryDAOFactory getInstance() {
        return INSTANCE;
    }

    UserDAO getUserDAO();

    BookDAO getBookDAO();
}

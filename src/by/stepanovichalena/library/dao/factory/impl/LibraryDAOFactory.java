package by.stepanovichalena.library.dao.factory.impl;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.impl.BookDAOImpl;
import by.stepanovichalena.library.dao.impl.UserDAOImpl;

public class LibraryDAOFactory implements by.stepanovichalena.library.dao.factory.LibraryDAOFactory {

    private LibraryDAOFactory() {
    }

    private static class BookDAOFactoryHolder {
        public static final LibraryDAOFactory HOLDER_INSTANCE = new LibraryDAOFactory();
    }

    public static LibraryDAOFactory getInstance() {
        return BookDAOFactoryHolder.HOLDER_INSTANCE;
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public BookDAO getBookDAO() {
        return new BookDAOImpl();
    }

}

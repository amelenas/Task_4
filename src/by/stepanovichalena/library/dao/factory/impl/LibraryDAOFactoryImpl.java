package by.stepanovichalena.library.dao.factory.impl;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.factory.LibraryDAOFactory;
import by.stepanovichalena.library.dao.impl.BookDAOImpl;
import by.stepanovichalena.library.dao.impl.UserDAOImpl;

public class LibraryDAOFactoryImpl implements LibraryDAOFactory {

    private LibraryDAOFactoryImpl() {
    }

    private static class BookDAOFactoryHolder {
        public static final LibraryDAOFactoryImpl HOLDER_INSTANCE = new LibraryDAOFactoryImpl();
    }

    public static LibraryDAOFactoryImpl getInstance() {
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

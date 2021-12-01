package by.stepanovichalena.library.dao.factory.impl;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.factory.LibraryDAOFactory;
import by.stepanovichalena.library.dao.impl.BookDAOImpl;
import by.stepanovichalena.library.dao.impl.UserDAOImpl;

public class LibraryDAOFactoryImpl implements LibraryDAOFactory {
    private UserDAO userDAO = new UserDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();

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
        return userDAO;
    }

    @Override
    public BookDAO getBookDAO() {
        return bookDAO;
    }

    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}

package by.stepanovichalena.library.dao.factory;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.impl.BookDAOImpl;

public class BookDAOFactory {

    private BookDAOFactory() {
    }

    private static class BookDAOFactoryHolder {
        public static final BookDAO HOLDER_INSTANCE = new BookDAOImpl();
    }

    public static BookDAO getInstance() {
        return BookDAOFactoryHolder.HOLDER_INSTANCE;
    }

}

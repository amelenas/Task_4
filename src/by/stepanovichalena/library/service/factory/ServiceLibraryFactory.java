package by.stepanovichalena.library.service.factory;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.impl.BookServiceImpl;
import by.stepanovichalena.library.service.impl.UserServiceImpl;

public class ServiceLibraryFactory {

    private ServiceLibraryFactory() {
    }

    private static class ServiceFactoryHolder {
        public static final ServiceLibraryFactory HOLDER_INSTANCE = new ServiceLibraryFactory();
    }

    public static ServiceLibraryFactory getInstance() {
        return ServiceLibraryFactory.ServiceFactoryHolder.HOLDER_INSTANCE;
    }

    public UserService getUserService(UserDAO userDAO) {
        return new UserServiceImpl(userDAO);
    }

    public BookService getBookService(BookDAO bookDAO) {
        return new BookServiceImpl(bookDAO);
    }

}
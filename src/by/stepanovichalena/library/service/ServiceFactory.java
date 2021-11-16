package by.stepanovichalena.library.service;

import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.util.BookService;
import by.stepanovichalena.library.service.util.UserService;

public class ServiceFactory {
    private static ServiceFactory INSTANCE;
    private BookService bookService;
    private UserService userService;

    private ServiceFactory() throws ServiceException {
        bookService = new BookServiceImpl();
        userService = new UserServiceImpl();
    }

    public static ServiceFactory getInstance() throws ServiceException {
        synchronized (ServiceFactory.class) {
            if (INSTANCE == null) {
                INSTANCE = new ServiceFactory();
            }
        }
        return INSTANCE;
    }
    public BookService getBookService() {
        return bookService;
    }

    public UserService getClientService() {
        return userService;
    }

}
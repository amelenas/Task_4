package by.stepanovichalena.library.service;

import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.util.BookService;
import by.stepanovichalena.library.service.util.UserService;

public class ServiceFactory {
    private static ServiceFactory INSTANCE;
    BookService bookService;
    UserService userService;

    private ServiceFactory() throws ServiceException {
        try {
            bookService = new BookServiceImpl();
            userService = new UserServiceImpl();
        } catch (LogicException e) {
            throw new ServiceException(e);
        }
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
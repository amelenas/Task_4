package by.stepanovichalena.library.service.factory;

import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.impl.BookServiceImpl;

public class ServiceBookFactory {

    private ServiceBookFactory() {
    }

    private static class ServiceFactoryHolder {
        public static final BookService HOLDER_INSTANCE = new BookServiceImpl();
    }

    public static BookService getInstance() {
        return ServiceBookFactory.ServiceFactoryHolder.HOLDER_INSTANCE;
    }

}
package by.stepanovichalena.library.service.factory;

import by.stepanovichalena.library.service.UserService;
import by.stepanovichalena.library.service.impl.UserServiceImpl;

public class ServiceUserFactory {

    private ServiceUserFactory() {
    }

    private static class ServiceFactoryHolder {
        public static final UserService HOLDER_INSTANCE = new UserServiceImpl();
    }

    public static UserService getInstance() {
        return ServiceUserFactory.ServiceFactoryHolder.HOLDER_INSTANCE;
    }

}
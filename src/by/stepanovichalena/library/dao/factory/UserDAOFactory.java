package by.stepanovichalena.library.dao.factory;

import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.impl.UserDAOImpl;

public class UserDAOFactory {

    private UserDAOFactory() {
    }

    private static class DAOFactoryHolder {
        public static final UserDAO HOLDER_INSTANCE = new UserDAOImpl();
    }

    public static UserDAO getInstance() {
        return DAOFactoryHolder.HOLDER_INSTANCE;
    }

}

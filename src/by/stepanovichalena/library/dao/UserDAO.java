package by.stepanovichalena.library.dao;

import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.source.UserSource;

public interface UserDAO {

    boolean register(User user) throws LibraryDAOException;

    boolean logIn(User user);

    void connectUserSource (UserSource userSource) throws LibraryDAOException;
}

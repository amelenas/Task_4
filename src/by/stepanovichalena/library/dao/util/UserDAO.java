package by.stepanovichalena.library.dao.util;

import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.entity.User;

import java.util.Collection;

public interface UserDAO {
    Collection<User> readAll() throws DAOException;

    void writeAll(Collection<User> users) throws DAOException;

}

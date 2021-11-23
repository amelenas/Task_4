package by.stepanovichalena.library.source;

import by.stepanovichalena.library.source.exception.DAOException;
import by.stepanovichalena.library.entity.User;

import java.util.Collection;

public interface UserSource {
    Collection<User> readAll() throws DAOException;

    void writeAll(Collection<User> users) throws DAOException;

}

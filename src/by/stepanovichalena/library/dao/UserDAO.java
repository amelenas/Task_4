package by.stepanovichalena.library.dao;

import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;

public interface UserDAO {

    boolean register(User user) throws LibraryDAOException;

    User logIn(User user) throws LibraryDAOException;

    boolean changeUserLevel(User user) throws LibraryDAOException;

    boolean userSearch(User user) throws LibraryDAOException;
}

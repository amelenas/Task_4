package by.stepanovichalena.library.dao;

import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;

public interface UserDAO {

    boolean register(User user) throws LibraryDAOException;

    boolean logIn(User user) throws LibraryDAOException;

    boolean isLevelAdmin(User user);

    boolean changeUsersLevel(User user) throws LibraryDAOException;

    boolean userSearch(User user) throws LibraryDAOException;
}

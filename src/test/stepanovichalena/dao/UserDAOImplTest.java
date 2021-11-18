package test.stepanovichalena.dao;

import by.stepanovichalena.library.dao.UserDAOImpl;
import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import org.junit.Test;

import java.util.Collection;

public class UserDAOImplTest {

    @Test
    public void readAll() throws DAOException {
        Collection<User> red = UserDAOImpl.getInstance().readAll();
        System.out.println(red);
    }

    @Test
    public void writeAll() throws DAOException {
        User user = new User("Test", "123456", AccessLevel.USER);
        Collection<User> users = UserDAOImpl.getInstance().readAll();
        users.add(user);
        UserDAOImpl.getInstance().writeAll(users);
    }
}
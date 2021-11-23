package test.stepanovichalena.dao;

import by.stepanovichalena.library.source.impl.UserSourceImpl;
import by.stepanovichalena.library.source.exception.DAOException;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import org.junit.Test;

import java.util.Collection;

public class UserSourceTest {

    @Test
    public void readAll() throws DAOException {
        Collection<User> red = new UserSourceImpl().readAll();
        System.out.println(red);
    }

    @Test
    public void writeAll() throws DAOException {
        User user = new User("Test", "123456", AccessLevel.USER);
        Collection<User> users = new UserSourceImpl().readAll();
        users.add(user);
        new UserSourceImpl().writeAll(users);
    }

    @Test
    public void writeAllNull() throws DAOException {
        User user = new User("Test", "123456", AccessLevel.USER);
        Collection<User> users = new UserSourceImpl().readAll();
        users.add(user);
        new UserSourceImpl().writeAll(null);
    }
}
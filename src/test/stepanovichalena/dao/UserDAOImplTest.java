package test.stepanovichalena.dao;

import by.stepanovichalena.library.dao.UserDAOImpl;
import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class UserDAOImplTest {

    @Test
    public void readAll() throws IOException {
        Collection<User> red = UserDAOImpl.getInstance().readAll();
        System.out.println(red);
    }

    @Test
    public void writeAll() throws DAOException {
        User user = new User("Test", "123456", AccessLevel.USER);
        Set<User> users = new TreeSet<>();
        users.add(user);
        UserDAOImpl.getInstance().writeAll(users);
    }
}
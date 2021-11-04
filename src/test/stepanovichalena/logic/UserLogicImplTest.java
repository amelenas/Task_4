package test.stepanovichalena.logic;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.logic.UserLogicImpl;
import by.stepanovichalena.library.logic.exception.LogicException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserLogicImplTest {

    @Test
    public void register() throws LogicException {
        UserLogicImpl userLogic = new UserLogicImpl();
        assertTrue(userLogic.register(new User("Test2", "32165", AccessLevel.ADMIN)));
    }

    @Test
    public void logIn() throws LogicException {
        UserLogicImpl userLogic = new UserLogicImpl();
        assertTrue(userLogic.logIn(new User("Test2", "32165", AccessLevel.ADMIN)));

    }
    @Test
    public void logInInvalidPassword() throws LogicException {
        UserLogicImpl userLogic = new UserLogicImpl();
        assertFalse(userLogic.logIn(new User("Test2", "321", AccessLevel.ADMIN)));

    }
}
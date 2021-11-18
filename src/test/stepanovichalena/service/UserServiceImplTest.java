package test.stepanovichalena.service;

import by.stepanovichalena.library.service.UserServiceImpl;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.junit.Test;

public class UserServiceImplTest {

    @Test
    public void register() throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.register("ServiceUserTest", "Password"));
    }

    @Test
    public void logIn() throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.logIn("ServiceUserTest", "Password"));
    }
}
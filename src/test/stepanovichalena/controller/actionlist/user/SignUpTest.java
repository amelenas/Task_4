package test.stepanovichalena.controller.actionlist.user;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.user.SignUp;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import org.junit.Test;

public class SignUpTest {
    LibraryDAOFactoryImpl libraryDAO = LibraryDAOFactoryImpl.getInstance();

    @Test
    public void executeUser() throws ControllerException {
        SignUp signUp = new SignUp(libraryDAO.getUserDAO());
        signUp.request("TestSignUp", "TestSignUp");
        System.out.println(signUp.execute());
   }

    @Test
    public void executeNull() throws ControllerException {
        SignUp signUp = new SignUp(libraryDAO.getUserDAO());
        signUp.request(null, null);
        System.out.println(signUp.execute());
    }
}

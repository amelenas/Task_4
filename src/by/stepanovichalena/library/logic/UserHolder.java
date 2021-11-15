package by.stepanovichalena.library.logic;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;

public class UserHolder {
    private static User user = new User("none", "none", AccessLevel.DEFAULT);

    public static void setUser(User u) {
        if (u != null) {
            user = u;
        }
    }

    public static AccessLevel getUsersLevel() {
        return user.getAccessLevel();
    }

}

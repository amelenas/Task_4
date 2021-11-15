package by.stepanovichalena.library.dao;

import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.User;
import by.stepanovichalena.library.dao.util.UserDAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UserDAOImpl implements UserDAO {
    private static final String PATH_TO_USERS_LIST = "resource/users.txt";
    public final static String DIVIDER = "/";

    private UserDAOImpl() {
    }

    private static class UserSourceActionHolder {
        public static final UserDAOImpl HOLDER_INSTANCE = new UserDAOImpl();
    }

    public static UserDAOImpl getInstance() {
        return UserSourceActionHolder.HOLDER_INSTANCE;
    }

    @Override
    public Collection<User> readAll() throws IOException {
        Set<User> users = new TreeSet<>();
        List<String> textLines = Files.readAllLines(Paths.get(PATH_TO_USERS_LIST));
        for (String line : textLines) {
            User user = parse(line);
            if (user != null){
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void writeAll(Collection<User> users) throws DAOException {
       try (FileOutputStream writer = new FileOutputStream(PATH_TO_USERS_LIST, false)) {
        for (User user: users) {
            String str = String.join(DIVIDER, user.getName(), user.getPassword(), user.getAccessLevel().toString(), "\n");
            writer.write(str.getBytes());
        }
       } catch (IOException e) {
          throw new DAOException("Can't write file", e);
       }
    }

    private User parse(String line) {
        User client = null;
        if (line != null || !line.equals("")) {
            String[] array = line.split(DIVIDER);
            if (array.length > 2){
            client = new User(array[0], array[1], AccessLevel.valueOf(array[2].trim().toUpperCase()));}
        }
        return client;
    }
}

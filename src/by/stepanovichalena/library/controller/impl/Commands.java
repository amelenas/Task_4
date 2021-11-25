package by.stepanovichalena.library.controller.impl;

import by.stepanovichalena.library.controller.Actions;
import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.impl.actionlist.WrongRequest;
import by.stepanovichalena.library.controller.impl.actionlist.book.DeleteBook;
import by.stepanovichalena.library.controller.impl.actionlist.book.AddBook;
import by.stepanovichalena.library.controller.impl.actionlist.book.FindBook;
import by.stepanovichalena.library.controller.impl.actionlist.book.ShowAll;
import by.stepanovichalena.library.controller.impl.actionlist.user.LogIn;
import by.stepanovichalena.library.controller.impl.actionlist.user.SignUp;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.UserDAO;
import by.stepanovichalena.library.dao.factory.LibraryDAOFactory;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class Commands {
    private UserDAO userDAO;
    private BookDAO bookDAO;
    private Map<Actions, Command> commands;

    public Commands(LibraryDAOFactory libraryDAOFactory) {
        this.userDAO = libraryDAOFactory.getUserDAO();
        this.bookDAO = libraryDAOFactory.getBookDAO();
        this.commands = new HashMap<>();
        {
            commands.put(Actions.SIGN_UP, new SignUp(userDAO));
            commands.put(Actions.LOG_IN, new LogIn(userDAO));
            commands.put(Actions.ADD_BOOK, new AddBook(bookDAO));
            commands.put(Actions.FIND_BOOK, new FindBook(bookDAO));
            commands.put(Actions.DELETE_BOOK, new DeleteBook(bookDAO));
            commands.put(Actions.SHOW_ALL, new ShowAll(bookDAO));
            commands.put(Actions.WRONG_REQUEST, new WrongRequest());
        }
    }

    public Command getCommand(Actions actions){
        Command command;
        if (actions == null) {
            command = commands.get(Actions.WRONG_REQUEST);
        } else {
            command = commands.get(actions);
        }
        return command;
    }

    public Command addCommand(Actions action, Command command){
        if (commands.containsValue(action)) {
           throw new ConcurrentModificationException("Command already exists");
        } else {
            commands.put(action, command);
        }
        return command;
    }
    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Map<Actions, Command> getCommands() {
        return commands;
    }

    public void setCommands(Map<Actions, Command> commands) {
        this.commands = commands;
    }
}

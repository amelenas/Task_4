package by.stepanovichalena.library.view;

import by.stepanovichalena.library.controller.Actions;
import by.stepanovichalena.library.controller.Controller;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.Commands;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactoryImpl;
import by.stepanovichalena.library.entity.AccessLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class View {
    private static final Logger LOGGER = LogManager.getLogger(View.class);

    public static void main(String[] args) {
        String actionInputted;
        Actions actions;
        String response = "";

        Scanner scanner = new Scanner(System.in);
        LibraryDAOFactoryImpl libraryDAOFactory = LibraryDAOFactoryImpl.getInstance();
        Commands commands = new Commands(libraryDAOFactory);
        Controller controller = new Controller(commands);

        do {
            ArrayList<String> request = new ArrayList<>();
            System.out.println("The list of available actions: " + Arrays.toString(Actions.values()));
            System.out.println("Please input action");
            actionInputted = scanner.nextLine();
            try {
                actions = Actions.valueOf(actionInputted.toUpperCase());
            } catch (IllegalArgumentException | NullPointerException e) {
                actions = Actions.WRONG_REQUEST;
            }
            if (actions != Actions.SHOW_ALL && actions != Actions.WRONG_REQUEST) {
                switch (actions) {
                    case CHANGE_USER_LEVEL:
                        System.out.println("Please input user name");
                        String userName = scanner.nextLine();
                        request.add(userName);
                        System.out.println("Please input access level ADMIN or USER");
                        String accessLevel = scanner.nextLine();
                        try {
                            AccessLevel.valueOf(accessLevel.toUpperCase());
                            request.add(accessLevel);
                        } catch (IllegalArgumentException | NullPointerException e) {
                            actions = Actions.WRONG_REQUEST;
                        }
                        break;
                    case DELETE_BOOK_BY_ID:
                        System.out.println("Please enter the ID of the book to delete");
                        String id = scanner.nextLine();
                        request.add(id);
                        break;
                    case FIND_BOOK_BY_AUTHOR:
                        System.out.println("Please input authors name");
                        String authorsName = scanner.nextLine();
                        request.add(authorsName);
                        break;
                    case FIND_BOOK_BY_TITLE:
                        System.out.println("Please input title of the book");
                        String title = scanner.nextLine();
                        request.add(title);
                        break;
                    case LOG_IN:
                    case SIGN_UP:
                        System.out.println("Please input User name");
                        String usersName = scanner.nextLine();
                        request.add(usersName);
                        System.out.println("Please input password, it must be at least 5 and no more than 20 symbols");
                        String password = scanner.nextLine();
                        request.add(password);
                        break;
                    default:
                        System.out.println("Please input Title of the book");
                        String bookTitle = scanner.nextLine();
                        request.add(bookTitle);
                        System.out.println("Please input Authors name");
                        String author = scanner.nextLine();
                        request.add(author);
                }
            }
            String[] requests = request.toArray(new String[0]);
            LOGGER.info(Arrays.toString(requests));
            try {
                response = controller.start(actions, requests);
            } catch (ControllerException e) {
                e.printStackTrace();
            }
            System.out.println(response);
        } while (!actionInputted.equalsIgnoreCase("exit"));
    }
}




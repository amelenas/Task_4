package by.stepanovichalena.library.view;

import by.stepanovichalena.library.controller.Actions;
import by.stepanovichalena.library.controller.Controller;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.Commands;
import by.stepanovichalena.library.dao.factory.impl.LibraryDAOFactory;
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
        LibraryDAOFactory libraryDAOFactory = LibraryDAOFactory.getInstance();
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
                if (actions == Actions.LOG_IN || actions == Actions.SIGN_UP) {
                    System.out.println("Please input User name");
                    String userName = scanner.nextLine();
                    request.add(userName);
                    System.out.println("Please input password, it must be at least 5 and no more than 20 symbols");
                    String password = scanner.nextLine();
                    request.add(password);
                } else {
                    System.out.println("Please input Title of the book");
                    String title = scanner.nextLine();
                    request.add(title);
                    System.out.println("Please input Authors name");
                    String authorsName = scanner.nextLine();
                    request.add(authorsName);
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




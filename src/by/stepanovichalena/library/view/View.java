package by.stepanovichalena.library.view;

import by.stepanovichalena.library.controller.Actions;
import by.stepanovichalena.library.controller.Controller;
import by.stepanovichalena.library.controller.exception.ControllerException;

import java.util.Arrays;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
        String actionInputted;
        Actions actions;
        String firstRequest = "";
        String secondRequest = "";
        String response = "";
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        do {
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
                    firstRequest = scanner.nextLine();
                    System.out.println(firstRequest);
                    System.out.println("Please input password, it must be at least 5 and no more than 20 symbols");
                    secondRequest = scanner.nextLine();
                    System.out.println(secondRequest);
                } else {
                    System.out.println("Please input Title of the book");
                    firstRequest = scanner.nextLine();
                    System.out.println("Please input Authors name");
                    secondRequest = scanner.nextLine();
                }
            }
            try {
                response = controller.start(actions, firstRequest, secondRequest);
            } catch (ControllerException e) {
                e.printStackTrace();
            }
            System.out.println(response);
        } while (!actionInputted.equalsIgnoreCase("exit"));
    }
}




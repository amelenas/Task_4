package by.stepanovichalena.library.view;

import by.stepanovichalena.library.controller.Actions;
import by.stepanovichalena.library.controller.Controller;
import by.stepanovichalena.library.controller.exception.ControllerException;

import java.util.Arrays;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
        String request;
        String response;
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        do {
            System.out.println("The list of available actions: " + Arrays.toString(Actions.values()));
            System.out.println("To create or search book, please, input divider \"/\" between title and author's name");
            request = scanner.nextLine();
            try {
                response = controller.start(request);
            } catch (ControllerException e) {
                response = e.getMessage();
            }
            System.out.println(response);
        } while (!request.equalsIgnoreCase("exit"));
    }
}


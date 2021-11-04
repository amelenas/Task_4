package by.stepanovichalena.library.view;


import by.stepanovichalena.library.controller.Actions;
import by.stepanovichalena.library.controller.Controller;
import by.stepanovichalena.library.service.exception.ServiceException;

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
            request = scanner.nextLine();
            try {
                response = controller.start(request);
            } catch (ServiceException e) {
                response = e.getMessage();
            }
            System.out.println(response);
        } while (!request.equalsIgnoreCase("exit"));
    }
}


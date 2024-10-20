import java.util.*;
import controller.*;

public class Idk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        PostController postController = new PostController();

        int isLoggedIn = -1;

        System.out.println("Welcome to Real Estate Application");

        while (isLoggedIn == -1) {
            printMenu();
            int choice = getValidInput(scanner, new int[]{0, 1, 2});

            switch (choice) {
                case 1:
                    userController.registerUser(scanner);
                    break;

                case 2:
                    handleLogin(scanner, userController);
                    break;

                case 0:
                    System.out.println("Exiting application. Thank you!");
                    scanner.close();
                    return;
            }
        }
    }  

    private static void printMenu() {
        System.out.println("Please enter a number to proceed:\n- Register (1)\n- Login (2)\n- Exit (0)");
    }

    private static void handleLogin(Scanner scanner, UserController userController) {
        printLoginMenu();
        int loginChoice = getValidInput(scanner, new int[]{1, 2, 0});
        int userId = userController.loginClient(scanner);

        switch (loginChoice) {
            case 1:
                if (userId >= 1) {
                    System.out.println("Logged in as a client successfully.");
                    postController.handleClient(userId);
                } else {
                    System.out.println("Login failed. Please try again.");
                }
                break;

            case 2:
                if (userController.loginAdmin(scanner)) {
                    System.out.println("Logged in as an admin successfully.");
                } else {
                    System.out.println("Login failed. Please try again.");
                }
                break;

            case 0:
                System.out.println("You went back.");
                break;

            default:
                System.out.println("Invalid choice. Please enter one of the following: 1, 2 or 0");
                break;
        }
    }

    private static void printLoginMenu() {
        System.out.println("Login in as: \n- Client (1)\n- Admin (2)\n- Go back (0)");
    }

    private static int getValidInput(Scanner scanner, int[] validChoices) {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); 

                if (isValidChoice(input, validChoices)) {
                    return input;
                }

                System.out.println("Invalid choice. Please enter one of the following: " + arrayToString(validChoices));

            } catch (InputMismatchException e) {
                System.err.println("Please enter a number.");
                scanner.next(); 
            }
        }
    }

    private static boolean isValidChoice(int input, int[] validChoices) {
        for (int validChoice : validChoices) {
            if (input == validChoice) {
                return true;
            }
        }
        return false;
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
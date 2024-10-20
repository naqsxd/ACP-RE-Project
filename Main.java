import java.util.*;
import view.*;
import controller.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        PostController postController = new PostController();

        int isLoggedIn = -1; // Could have made it a boolean, but if its an int we can use it to check if the user is a client or an admin by the value it returns
    
        System.out.println("Welcome to Real Estate Application");
    
        while (isLoggedIn==-1) {
            System.out.println("Please enter a number to proceed:\n- Register (1)\n- Login (2)\n- Exit (0)");
            int choice = getValidInput(scanner, new int[]{0, 1, 2});
            
            switch (choice) {

                case 1:
                    userController.registerUser(scanner);
                    break;

                case 2:
                    int userId = handleLogin(scanner, userController);
                    if (userId >= 1) {
                        isLoggedIn = userId;
                        postController.handleClient(userId);
                        break;
                    } 
                    // else if (isLoggedIn == 2) {
                    //     // AdminView.showAdminMenu();
                    //     // break;
                    // }
                    
                    isLoggedIn = -1;
                    break;

                case 0:
                    System.out.println("Exiting application. Thank you!");
                    scanner.close();
                    return;  // Exit the application
            }
        }
    }  
    
    public static int handleLogin(Scanner scanner, UserController userController){
        
        System.out.println("Login in as: \n- Client (1)\n- Admin (2)\n- Go back (0)");
        int loginChoice = getValidInput(scanner, new int[]{1, 2, 0});
        int userId = userController.loginClient(scanner);

        switch (loginChoice) {
            case 1:
                if (userId >= 1) {
                    System.out.println("Logged in as a client successfully.");
                    return userId; 
                } else {
                    System.out.println("Login failed. Please try again.");
                    return -1; 
                }


            case 2:
                if (userController.loginAdmin(scanner)) {
                    System.out.println("Logged in as an admin successfully.");
                    return 2;
                } else {
                    System.out.println("Login failed. Please try again.");
                    return -1;
                }

                
            case 0:
                System.out.println("You went back.");
                return -1;

            default:
                return -1;
        }

    }

    private static int getValidInput(Scanner scanner, int[] validChoices) {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); 

                for (int validChoice : validChoices) {
                    if (input == validChoice) {
                        return input;
                    }
                }

                System.out.println("Invalid choice. Please enter one of the following: " + arrayToString(validChoices));

            } catch (InputMismatchException e) {
                System.err.println("Please enter a number.");
                scanner.next(); 
            }
        }
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
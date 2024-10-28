import java.util.*;
import controller.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Real Estate Application!");

        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        PostController postController = new PostController();
        AdminController adminController = new AdminController();

        int isLoggedIn = -1;
    
        while (isLoggedIn==-1) {
            System.out.println("Enter a number:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit the Application");
            int choice = getValidInput(scanner, new int[]{1, 2, 3});
            
            switch (choice) {
                case 1:
                    userController.registerUser(scanner);
                    break;

                    case 2:
                    int userId = handleLogin(scanner, userController);
                
                        if (userId > 0) {  
                            System.out.println("Logged in as a client successfully.");
                            postController.handleClient(userId); 

                        } else if (userId == -2) {
                            System.out.println("Logged in as an admin successfully.");
                            adminController.handleAdmin(userId); 
                            
                        } else {
                            System.out.println("Login failed. Try again.");
                        }
                        break;

                case 3:
                    System.out.println("\nExiting application. Thank you!");
                    scanner.close();
                    return; 
            }
        }
    }  
    
    public static int handleLogin(Scanner scanner, UserController userController) {
        System.out.println("Login as:");
        System.out.println("1. Client");
        System.out.println("2. Admin");
        System.out.println("3. Go Back");
    
        int loginChoice = getValidInput(scanner, new int[]{1, 2, 3});
    
        if (loginChoice == 1) {
            int userId = userController.loginClient(scanner);  // Client login
            if (userId > 0) {
                return userId;  // Return userId for the client
            } else {
                return -1;  // Client login failed
            }
        } else if (loginChoice == 2) {
            boolean isAdmin = userController.loginAdmin(scanner);  // Admin login
            if (isAdmin) {
                return -2;  // Use -2 to indicate successful admin login
            } else {
                return -1;  // Admin login failed
            }
        } else {
            System.out.println("Going back to main menu.");
            return -1;  // If the user chooses to go back
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
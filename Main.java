import java.util.InputMismatchException;
import java.util.Scanner;
import model.*;
import view.*;
import controller.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        boolean isLoggedIn = false;

        System.out.println("\nWelcome to Real Estate Application");

        while (!isLoggedIn) {
            System.out.println("Please enter 1 for Registration or 2 for Login.");

            int choice = -1; 
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); 
                    if (choice == 1 || choice == 2) {
                        validInput = true; // Input is valid
                    } else {
                        System.out.println("Invalid choice. Please enter 1 for Register or 2 for Login.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Please enter a number.");
                    scanner.next(); // Clear the invalid input
                }
            }

            switch (choice) {
                case 1:
                    userController.registerUser(scanner);
                    break;

                case 2:
                    isLoggedIn = userController.loginUser(scanner);
                    if (isLoggedIn) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Login failed. Please try again.");
                        break;
                    }
                    
                default:
                    break;
            }

        }
    }  
    
    public static void showMenu(){
        boolean inMenu = true;
        Scanner scanner = new Scanner(System.in);
        PostController postController = new PostController();
        

        while (inMenu) {
            ClientView.showMenu();
            System.out.println("Choose an option: ");
            int menuChoice = scanner.nextInt();
            
            switch (menuChoice) {
                case 1:
                    postController.createPost();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Logging out...");
                    inMenu = false; // Exit the menu
                    break;
                default:
                    System.out.println("Feature not implemented yet.");
                    break;
            }
        }
        scanner.close();
    }
}

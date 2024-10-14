import java.util.Scanner;
import model.*;
import view.*;
import controller.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController(); // Create an instance of UserController
        boolean running = true;

        System.out.println("--- Welcome to the Real Estate System ---");
        System.out.println("1. Register");
        System.out.println("2. Login");

        
        while (running) {
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice==1) {
                System.out.println("Enter your name: ");
                String name = scanner.next();

                System.out.println("Enter your username: ");
                String username = scanner.next();

                System.out.println("Enter your email: ");
                String email = scanner.next();

                System.out.println("Enter your password: ");
                String password = scanner.next();

                userController.registerUser(name, username, email, password);
                showMenu();
                running = false;

            }else if (choice==2) {
                System.out.println("Enter your username: ");
                String username = scanner.next();

                System.out.println("Enter your password: ");
                String password = scanner.next();

                userController.loginUser(username, password);
                running = false;

            }else{
                System.out.println("Invalid choice. Please try again.");
            }

        }
        scanner.close();
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

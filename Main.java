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
            System.out.println("Please enter a number to proceed:\n   - Register (1)\n   - Login (2)");

            int choice = getValidInput(scanner, new int[]{1, 2});
            
            switch (choice) {
                case 1:
                    userController.registerUser(scanner);
                    break;

                case 2:
                    isLoggedIn = handleLogin(scanner, userController);
                    break;

                default:
                    break;
            }
        }
        System.out.println("Exiting application. Thank you!");
        scanner.close(); // Close the scanner resource
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

    public static boolean handleLogin(Scanner scanner, UserController userController){

        System.out.println("Login in as: \n   - Client (1)\n   - Admin (2)\n   - Go back (0)");

        int loginChoice = getValidInput(scanner, new int[]{1, 2, 0});
    
        switch (loginChoice) {
            case 1:
                if (userController.loginUser(scanner)) {
                    System.out.println("Logged in as a client successfully.");
                    return true; 
                } else {
                    System.out.println("Login failed. Please try again.");
                    return false; 
                }

            case 2:
                if (userController.loginAdmin(scanner)) {
                    System.out.println("Logged in as an admin successfully.");
                    return true;
                } else {
                    System.out.println("Login failed. Please try again.");
                    return false;
                }

            case 0:
                System.out.println("You went back.");
                return false;

            default:
                return false;
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
                System.err.println("Please enter a valid number.");
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





/*

                    switch (loginChoice) {
                        case 1:
                            isLoggedIn = userController.loginUser(scanner);
                            if (isLoggedIn) {
                                System.out.println("Login successful.");
                                // next step (POST)
                            } else {
                                System.out.println("Login failed. Please try again.");
                                break;
                            }
                            break;

                        case 2:
                            isAdminLoggedIn = userController.loginAdmin(scanner);
                            if (isAdminLoggedIn) {
                                System.out.println("Logged in as an admin successfully.");
                                isLoggedIn = true;
                                
                            } else {
                                System.out.println("Login failed. Please try again.");
                                break;
                            }
                            break;

                        case 0:
                            System.out.println("you went back");
                            break;

                        default:
                            break;
                    }

                    -----------------------------------

                    if (loginChoice==1) {
                        isLoggedIn = userController.loginUser(scanner);
                        if (isLoggedIn) {
                            System.out.println("Login successful.");
                            // next step (POST)
                        } else {
                            System.out.println("Login failed. Please try again.");
                            break;
                        }

                    }else if (loginChoice==2) {
                        isAdminLoggedIn = userController.loginAdmin(scanner);
                        if (isAdminLoggedIn) {
                            System.out.println("Logged in as an admin successfully.");
                            isLoggedIn = true;
                            
                        } else {
                            System.out.println("Login failed. Please try again.");
                            break;
                        }

                    }else if (loginChoice==0) {
                        System.out.println("you went back");
                        break;
                    }

                    */
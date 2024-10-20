package controller;

import view.AdminView;
import java.util.*;

public class AdminController {
    Scanner scanner = new Scanner(System.in);

    public void handleAdmin(int userId) {
        boolean isAdminActive = true;
        while (isAdminActive) {
            try {
                AdminView.showAdminMenu();
                
                System.out.println("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
    
                switch (choice) {
                    case 1:
                        manageUsers();
                        break;
    
                    case 2:
                        managePosts();
                        break;
    
                    case 3:  
                        System.out.println("Logging out.");
                        isAdminActive = false;
                        break;
                    
                    default:
                        System.out.println("Please enter a valid option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
            }
        }
    }


    private void manageUsers() {
        System.out.println("! [MANAGE USERS NOT IMPLEMENTED]");
    }

    private void managePosts() {
        System.out.println("! [MANAGE POSTS NOT IMPLEMENTED]");
    }
}
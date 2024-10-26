package controller;

import java.io.*;
import java.util.*;
import view.ProfileView;;
public class ProfileController {
    
    private int loggedInUserId;
    private final String userDataFile = "data\\UserData.txt"; 
    Scanner scanner = new Scanner(System.in);
   
    public ProfileController(int loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }

    public void handleProfile() {
        viewProfile();
        boolean isProfileActive = true;
        while (isProfileActive) {
            try {
                ProfileView.showProfileMenu();
                System.out.println("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
    
                switch (choice) {
                    case 1:
                        updateProfile();
                        break;
    
                    case 2:
                        ViewCreatedPosts();
                        break;
    
                    case 3:  
                        viewBookmarks();
                        break;

                    case 4:  
                        isProfileActive = false;
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
        
    
    public void viewProfile() {

        if (loggedInUserId == -1) { 
            System.out.println("No user is currently logged in.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(userDataFile))) {
            String line;
            boolean userFound = false;

            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                int userId = Integer.parseInt(userData[0]); 

                if (userId == loggedInUserId) {
                    // Display
                    System.out.println("\nUser Profile:");
                    System.out.println("Name: " + userData[2]);
                    System.out.println("Username: " + userData[5]);
                    System.out.println("Email: " + userData[6]);
                    System.out.println("Phone Number: " + userData[4]);
                    System.out.println("Address: " + userData[3]);

                    userFound = true;
                    break;
                }
            }

            if (!userFound) {
                System.out.println("User profile not found.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the user data file: " + e.getMessage());
        }
    }
    
    public void updateProfile() {
        System.out.println("NOT IMPLEMENTED");
    }
    
    public void deleteProfile() {
        System.out.println("NOT IMPLEMENTED");

    }

    public void ViewCreatedPosts() {
        System.out.println("NOT IMPLEMENTED");

    }

    public void ViewBookmarks() {
        System.out.println("NOT IMPLEMENTED");

    }

    public void viewBookmarks() {
        System.out.println("NOT IMPLEMENTED");

    }


}
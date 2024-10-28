package controller;

import java.io.*;
import java.util.*;
import view.ProfileView;


public class ProfileController {
    
    private int loggedInUserId;
    private final String userDataFile = "data\\UserData.txt"; 
    private final String postDataFile = "data\\PostData.txt";
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
        if (loggedInUserId == -1) { 
            System.out.println("No user is currently logged in.");
            return;
        }
    
        boolean postsFound = false;
    
        try (BufferedReader br = new BufferedReader(new FileReader(postDataFile))) {
            String line;
    
            System.out.println("\nYour Created Posts:");
            while ((line = br.readLine()) != null) {
                String[] postData = line.split(",");
                int postUserId = Integer.parseInt(postData[1]);
    
                if (postUserId == loggedInUserId) {
                    postsFound = true;

                    System.out.println("Post ID: " + postData[0]);
                    System.out.println("Title: " + postData[4]);
                    System.out.println("Type: " + postData[3]);
                    System.out.println("Listing Type: " + postData[2]);
                    System.out.println("Address: " + postData[8]);
                    System.out.println("Location: " + postData[6] + ", " + postData[7]);
                    System.out.println("Price: " + postData[9]);
                    System.out.println("Bedrooms: " + postData[10]);
                    System.out.println("Bathrooms: " + postData[11]);
                    System.out.println("Contact for the post: " + postData[14]);
                    System.out.println("---------------------------------");
                }
            }
    
            if (!postsFound) {
                System.out.println("You have not created any posts yet.");
            }
    
        } catch (IOException e) {
            System.out.println("An error occurred while reading the post data file: " + e.getMessage());
        }

    }

    public void viewBookmarks() {
        System.out.println("NOT IMPLEMENTED");

    }

}
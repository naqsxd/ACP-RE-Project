package controller;
import java.util.*;
import java.io.*;

import model.Post;
import controller.UserController;

public class PostController {

    Scanner scanner = new Scanner(System.in);
    UserController userController = new UserController();
    private HashMap<Integer, Post> posts;
    private final String postDataFile = "data\\PostData.txt";

    public PostController() {
        posts = new HashMap<>();
        loadPosts();
    }

    public void handleClient(int userId) {
        try{
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPost(userId);
                    break;

                case 2:
                    viewPost();
                    break;

                case 3:
                    updatePost();
                    break;

                case 4:
                    deletePost();
                    break;

                case 5:
                    userController.profilePage();
                    break;
            
                default:
                    System.out.println("Please enter a valid option");
                    handleClient(userId);
            }
        }catch(InputMismatchException e){
            System.out.println("Please enter a valid number");
            scanner.nextLine();
            handleClient(userId); 
        }
        
    }
    

    public void createPost(int userId) {
        try {
            System.out.println("Creating a new post...");

            int postId = posts.size() + 1;

            System.out.print("Enter post title: ");
            String title = scanner.nextLine();

            System.out.print("Enter post type (Apartment, House, Warehouse, or Others): ");
            String type = scanner.nextLine();

            System.out.print("Enter post listing type (Rent or Buy): ");
            String listingType = scanner.nextLine();

            System.out.print("Enter post description: ");
            String description = scanner.nextLine();

            System.out.print("Enter post price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline

            System.out.print("Enter property area size (in square meters): ");
            double area = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline

            System.out.print("Enter number of bedrooms: ");
            int bedrooms = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            System.out.print("Enter number of bathrooms: ");
            int bathrooms = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            System.out.print("Enter contact information: ");
            String contactInfo = scanner.nextLine();

            System.out.print("Enter post country: ");
            String country = scanner.nextLine();

            System.out.print("Enter post city: ");
            String city = scanner.nextLine();

            System.out.print("Enter post address: ");
            String address = scanner.nextLine();

            Post newPost = new Post(postId, userId, listingType, type, title, description, country, city, address, price, bedrooms, bathrooms, area, "Available", contactInfo);

            posts.put(postId, newPost);
            savePost();
            
            System.out.println("Post created successfully! Title: " + newPost.getTitle());

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data for each field.");
            scanner.nextLine();  // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred while creating the post: " + e.getMessage());
        }
    }
     
    public void viewPost() {
        System.out.println("Not implemented");
    }

    public void updatePost() {
        System.out.println("Not implemented");
    }

    public void deletePost() {
        System.out.println("Not implemented");
    }



    private void loadPosts() {
        File file = new File(postDataFile);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(postDataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 11) {

                    int postId = Integer.parseInt(data[0]);
                    int userId = Integer.parseInt(data[1]);
                    String listingType = data[2];
                    String type = data[3];
                    String title = data[4];
                    String description = data[5];
                    String country = data[6];
                    String city = data[7];
                    String address = data[8];
                    double price = Double.parseDouble(data[9]);
                    int bedrooms = Integer.parseInt(data[10]);
                    int bathrooms = Integer.parseInt(data[11]);
                    double area = Double.parseDouble(data[12]);
                    String contactInfo = data[13];

                    Post post = new Post(postId, userId, listingType, type, title, description, country, city, address, price, bedrooms, bathrooms, area, "Available", contactInfo);
                    posts.put(postId, post);  // Add the post to the list
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading post data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing post data: " + e.getMessage());
        }
    }

    private void savePost() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(postDataFile))) {
            for (Post post : posts.values()) {
                writer.write(post.getPostId() + "," + post.getUserId() + "," + post.getListingType() + "," + post.getType() + "," + post.getTitle() + "," + post.getDescription() + "," + post.getCountry() + "," + post.getCity() + "," + post.getAddress() + "," + post.getPrice() + "," + post.getBedrooms() + "," + post.getBathrooms() + "," + post.getArea() + "," + post.getStatus() + "," + post.getOwnerContactInfo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving post data: " + e.getMessage());
        }
    }
}
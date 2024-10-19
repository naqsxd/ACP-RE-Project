package controller;
import java.util.*;
import java.io.*;

import model.Post;

public class PostController {

    Scanner scanner = new Scanner(System.in);
    private List<Post> posts;
    private final String postDataFile = "data\\PostData.txt";

    public PostController() {
        posts = new ArrayList<>();
        loadPosts();
    }

    public void handleClient() {
        System.out.println("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createPost();
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
                searchPost();
                break;
        
            default:
                break;
        }
    }
    
    public void createPost() {
        System.out.println("Creating a new post...");

        System.out.print("Enter post title: ");
        String title = scanner.next();

        System.out.print("Enter post type: (Apartment, House, Warehouse, or )thers)");
        String type = scanner.next();

        System.out.print("Enter post listing type: (Rent or Buy)");
        String listingType = scanner.next();

        System.out.print("Enter post description: ");
        String description = scanner.next();

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
        String contactInfo = scanner.next();

        System.out.print("Enter post country: ");
        String country = scanner.next();

        System.out.print("Enter post city: ");
        String city = scanner.next();

        System.out.print("Enter post address: ");
        String address = scanner.next();

        Post newPost = new Post(1, 1, listingType, type, title, description, country, city, address, price, bedrooms, bathrooms, area, "Available", contactInfo);

        posts.add(newPost);
        savePost();
        
        System.out.println("Post created successfully! Title: " + newPost.getTitle());

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

    public void searchPost() {
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
                    posts.add(post);  // Add the post to the list
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
            for (Post post : posts) {
                writer.write(post.getPostId() + "," + post.getUserId() + "," + post.getListingType() + "," + post.getType() + "," + post.getTitle() + "," + post.getDescription() + "," + post.getCountry() + "," + post.getCity() + "," + post.getAddress() + "," + post.getPrice() + "," + post.getBedrooms() + "," + post.getBathrooms() + "," + post.getArea() + "," + post.getStatus() + "," + post.getOwnerContactInfo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving post data: " + e.getMessage());
        }
    }
}
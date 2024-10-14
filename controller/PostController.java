package controller;
import java.util.*;

import model.Post;

public class PostController {
    Scanner scanner = new Scanner(System.in);
    private List<Post> posts;

    public PostController() {
        this.posts = new ArrayList<>(); // Initialize the list
    }
    
    public void createPost() {
        System.out.println("Creating a new post...");

        System.out.print("Enter post title: ");
        String title = scanner.next();

        System.out.print("Enter post type: ");
        String type = scanner.next();

        System.out.print("Enter post listing type: ");
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
        
        System.out.println("Post created successfully! Title: " + newPost.getTitle());

    }
    
    
    
    public void viewPost() {

    }



    public void updatePost() {

    }



    public void deletePost() {

    }

}
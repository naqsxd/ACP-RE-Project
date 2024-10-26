package controller;
import java.util.*;
import java.io.*;
import model.*;
import view.*;

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
        ProfileController profileController = new ProfileController(userId);
        boolean isClientActive = true;
        while (isClientActive) {
            try {
                ClientView.showClientMenu();
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
                        profileController.handleProfile();
                        break;
    
                    case 6: 
                        System.out.println("\nLogging out.");
                        isClientActive = false;
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
    

    public void createPost(int userId) {
        try {
            System.out.println("\nCreating a new post...");
    
            int postId = posts.size() + 1;
    
            // Validate mandatory fields
            String title = validateInput(scanner, "Enter post title:", "^(?!\\s).+$", "Title cannot be empty.");

            String type = validateInput(scanner, "Enter post type (Apartment, House, Warehouse, or Others):", "^(Apartment|House|Warehouse|Others)$", "Invalid type. Please enter Apartment, House, Warehouse, or Others.");
            
            String listingType = validateInput(scanner, "Enter post listing type (Rent or Buy):", "^(Rent|Buy)$", "Invalid listing type. Please enter Rent or Buy.");
            
            double area = Double.parseDouble(validateInput(scanner, "Enter property area size (in square meters):","^[1-9]\\d*(\\.\\d+)?$", "Invalid area size. Please enter a positive number."));
            
            String contactInfo = validateInput(scanner, "Enter contact information:", "^(?!\\s).+$", "Contact information cannot be empty.");
            
            String country = validateInput(scanner, "Enter post country:", "^(?!\\s).+$", "Country cannot be empty.");
            
            String city = validateInput(scanner, "Enter post city:", "^(?!\\s).+$", "City cannot be empty.");
            
            String address = validateInput(scanner, "Enter post address:", "^(?!\\s).+$", "Address cannot be empty.");
    
            String description = validateInput(scanner, "Enter post description (optional):", "^(?!\\s).*$", "Description cannot be empty if provided.");
            
            double price = Double.parseDouble(validateInput(scanner, "Enter post price: ", "^[1-9]\\d*(\\.\\d+)?$", "Invalid price. Please enter a positive number."));
            
            int bedrooms = Integer.parseInt(validateInput(scanner, "Enter number of bedrooms: ", "^[0-9]+$", "Invalid number of bedrooms. Please enter a positive number."));
            
            int bathrooms = Integer.parseInt(validateInput(scanner, "Enter number of bathrooms: ", "^[0-9]+$", "Invalid number of bathrooms. Please enter a positive number."));
    
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
        if (posts.isEmpty()) {
            System.out.println("There are no posts.");
            return; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(postDataFile))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                
                String[] postDetails = line.split(",");

                String postId = postDetails[0];
                String title = postDetails[4];  
                String country = postDetails[6];  
                String city = postDetails[7];  
                String address = postDetails[8];  
                String price = postDetails[9];
                String listingType = postDetails[2];
                String status = postDetails[13];

                
                System.out.println(String.format("\n %s. %s | %s, %s, %s | $%s | %s | %s", postId, title, address, city, country, price, listingType, status));
                
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    public void updatePost() {
        if (posts.isEmpty()) {
            System.out.println("There are no posts.");
            return; 
        }

        viewPost();  // Display existing posts to the user
    
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter the ID of the post you want to update: ");
        int postId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
    
        if (posts.containsKey(postId)) {
            Post postToUpdate = posts.get(postId);  // Retrieve the post
    
            System.out.println("Updating post: " + postToUpdate.getTitle());
            
            boolean updating = true;
            while (updating) {
                System.out.println("Choose an option to update:");
                System.out.println("1. Title");
                System.out.println("2. Type");
                System.out.println("3. Listing Type");
                System.out.println("4. Description");
                System.out.println("5. Price");
                System.out.println("6. Area");
                System.out.println("7. Bedrooms");
                System.out.println("8. Bathrooms");
                System.out.println("9. Contact Info");
                System.out.println("10. Country");
                System.out.println("11. City");
                System.out.println("12. Address");
                System.out.println("13. Status"); 
                System.out.println("0. Done Updating"); 
    
                int updateOption = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
    
                switch (updateOption) {
                    case 1:
                        System.out.print("Enter new title: ");
                        postToUpdate.setTitle(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new type: ");
                        postToUpdate.setType(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter new listing type: ");
                        postToUpdate.setListingType(scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("Enter new description: ");
                        postToUpdate.setDescription(scanner.nextLine());
                        break;
                    case 5:
                        System.out.print("Enter new price: ");
                        postToUpdate.setPrice(scanner.nextDouble());
                        scanner.nextLine(); // Consume the newline character
                        break;
                    case 6:
                        System.out.print("Enter new area: ");
                        postToUpdate.setArea(scanner.nextDouble());
                        scanner.nextLine(); // Consume the newline character
                        break;
                    case 7:
                        System.out.print("Enter new number of bedrooms: ");
                        postToUpdate.setBedrooms(scanner.nextInt());
                        scanner.nextLine(); // Consume the newline character
                        break;
                    case 8:
                        System.out.print("Enter new number of bathrooms: ");
                        postToUpdate.setBathrooms(scanner.nextInt());
                        scanner.nextLine(); // Consume the newline character
                        break;
                    case 9:
                        System.out.print("Enter new contact information: ");
                        postToUpdate.setOwnerContactInfo(scanner.nextLine());
                        break;
                    case 10:
                        System.out.print("Enter new country: ");
                        postToUpdate.setCountry(scanner.nextLine());
                        break;
                    case 11:
                        System.out.print("Enter new city: ");
                        postToUpdate.setCity(scanner.nextLine());
                        break;
                    case 12:
                        System.out.print("Enter new address: ");
                        postToUpdate.setAddress(scanner.nextLine());
                        break;
                    case 13:
                        System.out.print("Enter new status: ");
                        postToUpdate.setStatus(scanner.nextLine());
                        break;
                    case 0:
                        updating = false; // Exit the loop to finish updating
                        break;
                    default:
                        System.out.println("Invalid option. No changes made.");
                        break;
                }
            }
    
            posts.put(postId, postToUpdate); // Update the HashMap
            savePost(); // Save changes to the file
            System.out.println("Post updated successfully!");
    
        } else {
            System.out.println("Post not found.");
        }
    }
    

    public void deletePost() {
        if (posts.isEmpty()) {
            System.out.println("There are no posts.");
            return; 
        }

        viewPost();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter the ID of the post you want to delete: ");
        int postId = scanner.nextInt();

        if (posts.containsKey(postId)) {
            posts.remove(postId);
            savePost();
            System.out.println("Post deleted successfully!");
        } else {
            System.out.println("Post not found.");
        }
    }


    private void loadPosts() {
        File file = new File(postDataFile);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(postDataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 15) {

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

    public String validateInput(Scanner scanner, String prompt, String regex, String errorMessage) {
        String input;
    
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();
    
            if (input.matches(regex)) {
                break; // Valid
            } else {
                System.out.println(errorMessage);
            }
        }
    
        return input;
    }
}
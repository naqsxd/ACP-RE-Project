// THINGS TO PUT TO CONSIDERATION:
// 1. Add validations to user input using regex.
// 2. Give more feedback to user, if an error occurs.

package controller;
import model.User;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class UserController {

    private HashMap<Integer, User> users; 
    private final String userDataFile = "data\\UserData.txt";

    public UserController() {
        users = new HashMap<>();
        loadUsers();
    }

    public void registerUser(Scanner scanner) {

        int userId = users.size() + 1;

        
        String name = validateInput(scanner, "Enter your name:", "[a-zA-Z]+([ ][a-zA-Z]+)*", "Invalid name. Please enter a valid name.");
        
        String username = uniqueUsernameChecker(scanner);
        
        String email = validateInput(scanner, "Enter your email:", "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", "Invalid email. Please enter a valid email.");
    
        String password = validateInput(scanner, "Enter your password:", "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", "Invalid password. Minimum eight characters, at least one letter and one number");

        String phoneNo = validateInput(scanner, "Enter your phone number (format: 000-000-0000):", "^\\d{3}-?\\d{3}-?\\d{4}$", "Invalid phone number format. Please use the format 000-000-0000.");

        String address = validateInput(scanner, "Enter your address:", "^.*$", "Invalid address. Please enter a valid address.");


        User newUser = new User(userId, 1, name, address, phoneNo, username, email, password);
        users.put(userId, newUser);
        saveUsers();

        System.out.println("Registered '" + newUser.getName() + "' successfully. Please login.");
    }

    public int loginClient(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        for (User user : users.values()) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password) && user.getRoleId()==1) {
                return user.getUserId(); 
            }
        }

        return -1; 
    }
    
    public boolean loginAdmin(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.next();

        System.out.println("Enter your password: ");
        String password = scanner.next();

        for (User user : users.values()) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password) && user.getRoleId() == 2) {
                return true;  
            }
        }

        return false; 
    }

    public void profilePage(){
        
    }

    public boolean isRegistered(String username){
        for(User user: users.values()){
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private String uniqueUsernameChecker(Scanner scanner){
        String username = validateInput(scanner, "Enter your username:", "^[a-zA-Z][a-zA-Z0-9_]{2,14}$", "Invalid username. It should start with a letter, be between 3 and 15 characters long, and contain only letters, digits, or underscores.");

        if (isRegistered(username)) {
            System.out.println("Username already taken. Please choose another one.");
            return uniqueUsernameChecker(scanner);
        }

        return username;
    }

    private void loadUsers(){
        File file = new File(userDataFile);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(userDataFile))){
            String line;
            while ((line=reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) {

                    int userId = Integer.parseInt(data[0]);
                    int roleId = Integer.parseInt(data[1]);
                    String name = data[2];
                    String address = data[3];
                    String phoneNumber = data[4];
                    String username = data[5];
                    String email = data[6];
                    String password = data[7];
                    
                    User user = new User(userId, roleId, name, address, phoneNumber, username, email, password);
                    users.put(userId, user); // Add the user to the list
                }
            }

        } catch (IOException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing user data: " + e.getMessage());
        }
    } 

    private void saveUsers(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userDataFile))){
            for(User user : users.values()){
                writer.write(user.getUserId() + "," + user.getRoleId() + "," + user.getName() + ","+ user.getAddress() + "," + user.getPhoneNumber() + ","+ user.getUsername() + "," + user.getEmail() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving user data: " + e.getMessage());
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
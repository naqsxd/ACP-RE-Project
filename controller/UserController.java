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

        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        
        String username = uniqueUsernameChecker(scanner);
        
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
    
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        System.out.println("Enter your phone number:");
        String phoneNo = scanner.nextLine();

        System.out.println("Enter your address:");
        String address = scanner.nextLine();


        User newUser = new User(userId, 1, name, address, phoneNo, username, email, password);
        users.put(userId, newUser);
        saveUsers();

        System.out.println("Registration successful! Welcome, " + newUser.getName() + "!");
    }

    public int loginClient(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.next();

        System.out.println("Enter your password: ");
        String password = scanner.next();

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
        System.out.println("Enter your username:");
        String username = scanner.nextLine();

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
}
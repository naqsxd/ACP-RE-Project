// Registers and logs in users
// writes new registered users to database
package controller;
import model.User;
import java.util.ArrayList;

public class UserController {

    private ArrayList<User> users;

    public UserController() {
        this.users = new ArrayList<>();
    }

    public void registerUser(String name, String username, String email, String password) {
        // For simplicity, we're just using a dummy ID
        int userId = users.size() + 1; // Simple incrementing ID
        User newUser = new User(userId, 1, name, "Address", "Phone", username, email, password);
        users.add(newUser);
        System.out.println("Registration successful! Welcome, " + newUser.getName() + "!");

    }


    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + user.getName() + "!");
                return true;
            }
        }
        System.out.println("Login failed. Invalid Credentials");
        return false;
    }
    
}
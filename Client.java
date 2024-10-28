import controller.AdminController;
import controller.PostController;
import controller.UserController;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        UserController userController = new UserController();
        PostController postController = new PostController();
        AdminController adminController = new AdminController();
        System.out.println("Welcome to Real Estate Application!");

        try (Socket socket = new Socket("localhost", 9999);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            // Step 1: Receive the initial message from the server
            System.out.println("Server: " + in.readLine());
            System.out.println("Server: " + in.readLine());
            System.out.println("Server: " + in.readLine());
            System.out.println("Server: " + in.readLine());

            // Step 2: Send the client's choice
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();
            out.println(choice);


            // Step 3: Receive additional option from server
            System.out.println("Server: " + in.readLine());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

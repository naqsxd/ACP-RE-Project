import controller.AdminController;
import controller.PostController;
import controller.UserController;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {

        UserController userController = new UserController();
        PostController postController = new PostController();
        AdminController adminController = new AdminController();
        Scanner scanner = new Scanner(System.in);


        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("Server started. Waiting for a client...");

            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                 System.out.println("Client connected!");

                out.println("Enter a number:");
                out.println("1. Register");
                out.println("2. Login");
                out.println("3. Exit the Application");

                // Step 2: Read client's response
                String clientResponse = in.readLine();
                System.out.println("Client chose: " + clientResponse);

                // Step 3: Based on the response, send another option
                if ("1".equalsIgnoreCase(clientResponse)) {
                    //out.println(userController.registerUser(scanner));
                } else if ("2".equalsIgnoreCase(clientResponse)) {
                    out.println("LOGIN");
                } else if ("3".equalsIgnoreCase(clientResponse)) {
                    out.println("BYE");
                } else {
                    out.println("Invalid choice, please respond with Coffee or Tea.");
                }



            } catch (IOException e) {
                System.out.println("Client disconnected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

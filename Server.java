import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.*;


public class Server{
    

    public static void main(String[] args) {
        UserController userController = new UserController();
        PostController postController = new PostController();
        AdminController adminController = new AdminController();
        Scanner scanner = new Scanner(System.in);

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is waiting for a client connection...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // read from client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // write to client

            String clientMessage;
            boolean isLoggedIn = false;

            while (!isLoggedIn) {
                out.println("1. Register");
                out.println("2. Login");
                out.println("3. Exit");
                out.println("Choose an option:");
                
                // Read the client's choice
                clientMessage = in.readLine();

                // Process the client's choice
                if ("1".equals(clientMessage)) {
                    out.println("Registration process initiated.");
                    userController.registerUser(scanner);

                } else if ("2".equals(clientMessage)) {
                    out.println("Login process initiated.");
                    int userId = handleLogin(scanner, userController);

                    if (userId > 0) {
                        out.println("Logged in as a client successfully.");
                        postController.handleClient(userId);
                        isLoggedIn = true;
                    }else if (userId == -2) {
                        System.out.println("Logged in as an admin successfully.");
                        adminController.handleAdmin(userId); 
                        
                    } else {
                        System.out.println("Login failed. Try again.");
                    }


                } else if ("3".equals(clientMessage)) {
                    out.println("Exiting...");
                    break;
                } else {
                    out.println("Invalid option. Please choose again.");
                }
            }



            
            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static int handleLogin(Scanner scanner, UserController userController) {
        System.out.println("Login as:");
        System.out.println("1. Client");
        System.out.println("2. Admin");
        System.out.println("3. Go Back");
    
        int loginChoice = getValidInput(scanner, new int[]{1, 2, 3});
    
        if (loginChoice == 1) {
            int userId = userController.loginClient(scanner);  // Client login
            if (userId > 0) {
                return userId;  // Return userId for the client
            } else {
                return -1;  // Client login failed
            }
        } else if (loginChoice == 2) {
            boolean isAdmin = userController.loginAdmin(scanner);  // Admin login
            if (isAdmin) {
                return -2;  // Use -2 to indicate successful admin login
            } else {
                return -1;  // Admin login failed
            }
        } else {
            System.out.println("Going back to main menu.");
            return -1;  // If the user chooses to go back
        }
    }
    

    private static int getValidInput(Scanner scanner, int[] validChoices) {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); 

                for (int validChoice : validChoices) {
                    if (input == validChoice) {
                        return input;
                    }
                }

                System.out.println("Invalid choice. Please enter one of the following: " + arrayToString(validChoices));

            } catch (InputMismatchException e) {
                System.err.println("Please enter a number.");
                scanner.next(); 
            }
        }
    }

    
    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }


}
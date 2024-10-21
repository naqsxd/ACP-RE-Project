import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 1234)) { 
            System.out.println("Connected to the server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            String serverMessage;

            while (true) {
                serverMessage = in.readLine();
                System.out.println(serverMessage);  
 
                if (serverMessage.contains("Choose an option:")) {
                    String choice = scanner.nextLine();
                    out.println(choice);
                }

                if (serverMessage.equals("Exiting...")) {
                    break;  // Exit the loop if server indicates to exit
                }
            }

            // Close connections
            in.close();
            out.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
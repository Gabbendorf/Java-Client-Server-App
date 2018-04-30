import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.String.*;

public class AppRunner {

    public static void main(String[] args) {
        String mode = args[0];
        int portNumber = 8080;
        if (mode.equals("server")) {
            try {
                // binds server socket to port
                ServerSocket serverSocket = new ServerSocket(portNumber);
                // outputs message for successful run
                System.out.println("--- Running echo server on port 8080 ---");
                // listens for connection
                Socket socket = serverSocket.accept();
                // gets input stream produced by client and reads sent message
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientMessage = bufferedReader.readLine();
                // closes socket
                socket.close();
                // outputs text from client
                System.out.println(format("[FROM Client] %s", clientMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                // creates and connects to socket at specified host and port.
                Socket socket = new Socket("localhost", portNumber);
                // outputs message for successful connection
                System.out.println("--- Connected echo server on port 8080 ---");
                // awaits user input via standard input stream
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                // saves input message
                String message = userInput.readLine();
                // sends message to server via output stream.
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                writer.println(message);
            } catch (ConnectException e) {
                System.err.println("Couldn't connect to server/server not running");
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
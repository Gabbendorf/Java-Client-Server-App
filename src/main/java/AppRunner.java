import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppRunner {

    private final static int portNumber = 8080;

    public static void main(String[] args) {
        String mode = args[0];
        if (mode.equals("client")) {
            try {
                Socket socket = new Socket("localhost", portNumber);
                Client client = new Client(new ClientSocket(socket));
                client.connect(System.in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Server server = new Server(new ListeningSocket(serverSocket));
                server.run();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

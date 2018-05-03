import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Server {

    private ServerSideSocket serverSocket;

    public Server(ServerSideSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run() {
        System.out.println("--- Running echo server on port 8080 ---");
        String clientText = readTextFromClient(listenForConnection());
        System.out.println(String.format("[FROM Client] %s", clientText));
    }


    public Socket listenForConnection() {
        return serverSocket.acceptConnection();
    }

    private BufferedReader waitForStreamFromClient(Socket clientSocket) {
        InputStreamReader streamReader = null;
        try {
            streamReader = new InputStreamReader(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new BufferedReader(streamReader);
    }

    public String readTextFromClient(Socket clientSocket) {
        String text = "";
        try {
            text = waitForStreamFromClient(clientSocket).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }
}

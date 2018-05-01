import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Server {

    private ServerSideSocket serverSocket;

    public Server(ServerSideSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket listenForConnection() {
        return serverSocket.accept();
    }

    private BufferedReader saveTextFromClient(InputStream clientText) {
        InputStreamReader streamReader = new InputStreamReader(clientText);
        return new BufferedReader(streamReader);
    }

    public String readTextFromClient(InputStream clientText) {
        String text = "";
        try {
            text = saveTextFromClient(clientText).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}

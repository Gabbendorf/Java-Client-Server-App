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

    public String readTextFromClient(InputStream clientText) {
        InputStreamReader streamReader = new InputStreamReader(clientText);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String text = "";
        try {
            text = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}

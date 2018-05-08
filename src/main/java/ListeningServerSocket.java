import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListeningServerSocket implements AcceptingSocket {

    private final ServerSocket serverSocket;

    public ListeningServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket acceptConnection() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}

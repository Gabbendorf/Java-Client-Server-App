import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListeningSocket implements ServerSideSocket {

    private final ServerSocket serverSocket;

    public ListeningSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
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

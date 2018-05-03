import java.net.Socket;

public class EchoServer {

    private final ReadingSocket socket;

    public EchoServer(ReadingSocket socket) {
        this.socket = socket;
    }

    public Socket listenForConnection() {
        return socket.acceptConnection();
    }
}

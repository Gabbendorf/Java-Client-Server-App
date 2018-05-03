import java.io.IOException;

public class EchoServer {

    private final ReadingSocket socket;

    public EchoServer(ReadingSocket socket) {
        this.socket = socket;
    }

    public WritingSocket listenForConnection() {
        return new ClientSideSocket(socket.acceptConnection());
    }

    public String readMessageFromClient(WritingSocket clientSocket) {
        String message = "";
        try {
            message = socket.getStreamFromClient(clientSocket).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}

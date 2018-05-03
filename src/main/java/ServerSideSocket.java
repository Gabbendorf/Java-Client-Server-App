import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSideSocket implements ReadingSocket {

    private final ServerSocket serverSocket;

    public ServerSideSocket(ServerSocket serverSocket) {
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

    @Override
    public BufferedReader getStreamFromClient(WritingSocket clientSocket) {
        InputStreamReader streamReader = new InputStreamReader(clientSocket.writtenStream());
        return new BufferedReader(streamReader);
    }
}

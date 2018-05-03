import java.io.BufferedReader;
import java.net.Socket;

public interface ReadingSocket {

    Socket acceptConnection();
    BufferedReader getStreamFromClient(WritingSocket clientSocket);
}

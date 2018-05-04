import java.net.Socket;

public interface ReadingSocket {

    Socket acceptConnection();
    String readStream(WritingSocket clientSocket);
}

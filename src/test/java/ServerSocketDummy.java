import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerSocketDummy implements ReadingSocket {

    public boolean isListening = false;

    @Override
    public Socket acceptConnection() {
        isListening = true;
        return new Socket();
    }

    @Override
    public BufferedReader getStreamFromClient(WritingSocket clientSocket) {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream("hello".getBytes())));
    }
}

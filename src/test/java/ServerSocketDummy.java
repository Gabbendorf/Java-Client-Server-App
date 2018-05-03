import java.net.Socket;

public class ServerSocketDummy implements ReadingSocket {

    public boolean isListening = false;

    @Override
    public Socket acceptConnection() {
        isListening = true;
        return new Socket();
    }
}

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket implements ClientSideSocket {

    private final Socket socket;

    public ClientSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public OutputStream writeStream() {
        OutputStream text = null;
        try {
            text = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public InputStream readStream() {
        InputStream text = null;
        try {
            text = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public Socket actualSocket() {
        return socket;
    }
}

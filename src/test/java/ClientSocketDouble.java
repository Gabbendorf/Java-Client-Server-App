import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientSocketDouble implements ClientSideSocket {

    private final String host;
    private final int portNumber;
    public boolean streamIsWritten = false;

    public ClientSocketDouble(String host, int portNumber) {
        this.host = host;
        this.portNumber = portNumber;
    }

    @Override
    public OutputStream writeStream() {
        streamIsWritten = true;
        return new ByteArrayOutputStream();
    }

    @Override
    public InputStream readStream() {
        return null;
    }

    @Override
    public Socket actualSocket() {
        return null;
    }
}

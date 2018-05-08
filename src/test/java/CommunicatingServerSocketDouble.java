import java.io.*;
import java.nio.charset.StandardCharsets;

public class CommunicatingServerSocketDouble implements ReadingSocket {

    private final String messageFromClient;

    public CommunicatingServerSocketDouble(String messageFromClient) {
        this.messageFromClient = messageFromClient;
    }

    @Override
    public String readStream() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input(messageFromClient)));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private InputStream input(String inputToRead) {
        return new ByteArrayInputStream(inputToRead.getBytes(StandardCharsets.UTF_8));
    }
}

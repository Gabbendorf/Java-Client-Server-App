import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommunicatingServerSocketDouble implements ReadingSocket {

    private final List<String> messagesFromClient;
    public boolean isClosed;

    public CommunicatingServerSocketDouble(String streamFromClient) {
        this.messagesFromClient = allMessages(streamFromClient);
    }

    @Override
    public String readStream() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream(message())));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        isClosed = true;
    }

    private String message() {
        String message = messagesFromClient.get(0);
        messagesFromClient.remove(message);
        return message;
    }

    private List<String> allMessages(String message) {
        List<String> allInputs = new ArrayList<>();
        for(String input : message.split("\n")) {
            allInputs.add(input);
        }
        return allInputs;
    }

    private InputStream inputStream(String inputToRead) {
        return new ByteArrayInputStream(inputToRead.getBytes(StandardCharsets.UTF_8));
    }
}

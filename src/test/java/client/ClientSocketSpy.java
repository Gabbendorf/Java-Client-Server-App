package client;

import java.util.ArrayList;
import java.util.List;

public class ClientSocketSpy implements WritingSocket {

    public boolean isClosed;
    public List<String> allMessagesWritten = new ArrayList<>();

    @Override
    public void writeToStream(String userInput) {
        allMessagesWritten.add(userInput);
    }

    @Override
    public void close() {
        isClosed = true;
    }
}

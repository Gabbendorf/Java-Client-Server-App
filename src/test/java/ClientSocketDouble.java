import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClientSocketDouble implements WritingSocket {

    private ByteArrayOutputStream outputStream;
    public boolean isClosed;
    public List<String> allMessagesWritten = new ArrayList<>();

    @Override
    public void writeToStream(String userInput) {
        outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        printWriter.println(userInput);
        allMessagesWritten.add(outputStream.toString());
    }

    @Override
    public void close() {
        isClosed = true;
    }
}

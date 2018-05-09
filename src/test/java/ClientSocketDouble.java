import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class ClientSocketDouble implements WritingSocket {

    private ByteArrayOutputStream outputStream;

    @Override
    public void writeToStream(String userInput) {
        outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        printWriter.println(userInput);
    }

    public String writtenMessage() {
        return outputStream.toString();
    }
}

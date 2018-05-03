import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ClientSocketSpy implements WritingSocket {

    private final String userInput;
    public boolean hasWrittenToOutputStream;

    public ClientSocketSpy(String userInput) {
        this.hasWrittenToOutputStream = false;
        this.userInput = userInput;
    }

    @Override
    public void writeToStream(String userInput) {
        hasWrittenToOutputStream = true;
    }

    @Override
    public InputStream writtenStream() {
        return new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
    }
}

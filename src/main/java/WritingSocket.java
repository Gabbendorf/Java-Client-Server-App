import java.io.InputStream;

public interface WritingSocket {

    void writeToStream(String userInput);
    InputStream writtenStream();
}

package client;

public interface WritingSocket {

    void writeToStream(String userInput);
    void close();
}

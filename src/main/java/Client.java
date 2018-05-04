import java.io.*;

public class Client {

    private final WritingSocket socket;
    private final ConsolePrinter consolePrinter;
    private final ConsoleReader consoleReader;

    public Client(WritingSocket socket, ConsolePrinter consolePrinter, ConsoleReader consoleReader) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
    }

    public void printConnectionMessage() {
        consolePrinter.connectionMessage();
    }

    public String readUserInput(InputStream inputStream) {
        return consoleReader.readInput(inputStream);
    }

    public void sendMessageToServer(String userInput) {
        socket.writeToStream(userInput);
    }
}

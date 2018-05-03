import java.io.*;

public class Client {

    private final WritingSocket socket;
    private final ConsolePrinter consolePrinter;

    public Client(WritingSocket socket, ConsolePrinter consolePrinter) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
    }

    public void printConnectionMessage() {
        consolePrinter.connectionMessage();
    }

    private BufferedReader userInput(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public String readUserInput(InputStream inputStream) {
        String userInput;
        try {
            userInput = userInput(inputStream).readLine();
        } catch (IOException error) {
            return error.getMessage();
        }
        return userInput;
    }

    public void sendMessageToServer(String userInput) {
        socket.writeToStream(userInput);
    }
}

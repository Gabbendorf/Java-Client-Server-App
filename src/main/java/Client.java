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

    public String readUserInput() {
        return consoleReader.readUserInput();
    }

    public void sendMessageToServer(String userInput) {
        socket.writeToStream(userInput);
    }
}

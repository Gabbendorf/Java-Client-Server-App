public class Client {

    private final WritingSocket socket;
    private final ConsolePrinter consolePrinter;
    private final ConsoleReader consoleReader;

    public Client(WritingSocket socket, ConsolePrinter consolePrinter, ConsoleReader consoleReader) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
    }

    public void run() {
        printConnectionMessage();
        sendMessageToServer();
    }

    private void printConnectionMessage() {
        consolePrinter.connectionMessage();
    }

    private void sendMessageToServer() {
        socket.writeToStream(consoleReader.readUserInput());
    }
}

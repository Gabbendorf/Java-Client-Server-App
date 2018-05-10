public class Client {

    private final WritingSocket socket;
    private final ConsolePrinter consolePrinter;
    private final StreamReader consoleReader;

    public Client(WritingSocket socket, ConsolePrinter consolePrinter, StreamReader consoleReader) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
    }

    public void run() {
        consolePrinter.printClientHasConnectedToServer();
        String userInput = consoleReader.readUserInput();
        while (!userInput.equals("#quit")) {
            socket.writeToStream(userInput);
            userInput = consoleReader.readUserInput();
        }
        endConnection();
    }

    private void endConnection() {
        socket.writeToStream("#quit");
        socket.close();
    }
}

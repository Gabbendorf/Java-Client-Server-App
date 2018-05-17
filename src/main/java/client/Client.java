package client;

import console.ConsolePrinter;
import console.ConsoleReader;

public class Client {

    private final WritingSocket socket;
    private final ConsolePrinter consolePrinter;
    private final ConsoleReader consoleReader;

    public Client(WritingSocket socket, ConsolePrinter consolePrinter, ConsoleReader consoleReader) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
    }

    public void connect() {
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

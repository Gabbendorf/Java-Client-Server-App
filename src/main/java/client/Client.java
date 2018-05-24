package client;

import console.ConsolePrinter;
import console.ConsoleReader;

public class Client {

    private final WritingSocket socket;
    private final ConsolePrinter consolePrinter;
    private final ConsoleReader consoleReader;
    private final String name;

    public Client(WritingSocket socket, ConsolePrinter consolePrinter, ConsoleReader consoleReader, String name) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
        this.name = name;
    }

    public void connect() {
        consolePrinter.printClientHasConnectedToServer();
        sendToServer(name);
        String userInput = consoleReader.readUserInput();
        while (!userInput.equals("#quit")) {
            sendToServer(userInput);
            userInput = consoleReader.readUserInput();
        }
        endConnection();
    }

    private void endConnection() {
        socket.writeToStream("#quit");
        socket.close();
    }

    private void sendToServer(String message) {
        socket.writeToStream(message);
    }
}

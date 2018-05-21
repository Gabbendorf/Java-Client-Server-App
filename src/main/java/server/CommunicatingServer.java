package server;
import console.ConsolePrinter;

public class CommunicatingServer implements Runnable {

    private final ReadingSocket socket;
    private final ConsolePrinter consolePrinter;

    public CommunicatingServer(ReadingSocket socket, ConsolePrinter consolePrinter) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
    }

    @Override
    public void run() {
        String clientName = getClientName();
        String clientMessage = messageFromClient();
        while (!clientMessage.equals("#quit")) {
            consolePrinter.printMessageFromClient(clientName, clientMessage);
            clientMessage = messageFromClient();
        }
        endConnectionWith(clientName);
    }

    private String getClientName() {
        return messageFromClient();
    }

    private String messageFromClient() {
        return socket.readStream();
    }

    private void endConnectionWith(String clientName) {
        socket.close();
        consolePrinter.clientLeftMessage(clientName);
    }
}

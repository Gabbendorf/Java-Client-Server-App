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
        consolePrinter.printServerIsRunning();
        String clientMessage = messageFromClient();
        while (!clientMessage.equals("#quit")) {
            consolePrinter.printMessageFromClient(clientMessage);
            clientMessage = messageFromClient();
        }
        socket.close();
    }

    private String messageFromClient() {
        return socket.readStream();
    }
}

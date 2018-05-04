import java.io.IOException;

public class EchoServer {

    private final ReadingSocket socket;
    private final ConsolePrinter consolePrinter;

    public EchoServer(ReadingSocket socket, ConsolePrinter consolePrinter) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
    }

    public WritingSocket listenForConnection() {
        return new ClientSideSocket(socket.acceptConnection());
    }

    public String readMessageFromClient(WritingSocket clientSocket) {
        String message = "";
        try {
            message = socket.getStreamFromClient(clientSocket).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void printIsRunningMessage() {
        consolePrinter.runningMessage();
    }

    public void printClientMessage(String clientMessage) {
        consolePrinter.printMessageFromClient(clientMessage);
    }
}

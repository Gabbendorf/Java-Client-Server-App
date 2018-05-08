public class CommunicatingServer {

    private final ReadingSocket socket;
    private final ConsolePrinter consolePrinter;

    public CommunicatingServer(ReadingSocket socket, ConsolePrinter consolePrinter) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
    }

    private String readMessageFromClient() {
        return socket.readStream();
    }

    public void printClientMessage() {
        consolePrinter.printMessageFromClient(readMessageFromClient());
    }
}

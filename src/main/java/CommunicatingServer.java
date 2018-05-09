public class CommunicatingServer {

    private final ReadingSocket socket;
    private final ConsolePrinter consolePrinter;

    public CommunicatingServer(ReadingSocket socket, ConsolePrinter consolePrinter) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
    }

    public void run() {
        printIsRunningMessage();
        printClientMessage();
    }

    private void printIsRunningMessage() {
        consolePrinter.runningMessage();
    }

    private String readMessageFromClient() {
        return socket.readStream();
    }

    private void printClientMessage() {
        consolePrinter.printMessageFromClient(readMessageFromClient());
    }
}

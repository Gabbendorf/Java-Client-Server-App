public class EchoServer {

    private final AcceptingSocket socket;
    private final ConsolePrinter consolePrinter;

    public EchoServer(AcceptingSocket socket, ConsolePrinter consolePrinter) {
        this.socket = socket;
        this.consolePrinter = consolePrinter;
    }

    public ReadingSocket listenForConnection() {
        return new CommunicatingServerSocket(socket.acceptConnection());
    }

    public void printIsRunningMessage() {
        consolePrinter.runningMessage();
    }
}

package server;

import console.ConsolePrinter;
import threads.MultiConnectionsExecutor;

public class EchoServer {

    private final AcceptingSocket socket;
    private final ConsolePrinter printer;
    private final MultiConnectionsExecutor threadsExecutor;

    public EchoServer(AcceptingSocket socket, ConsolePrinter printer, MultiConnectionsExecutor threadsExecutor) {
        this.socket = socket;
        this.printer = printer;
        this.threadsExecutor = threadsExecutor;
    }

    public void acceptSimultaneousConnectionsUpTo(int threadsNumber) {
        threadsExecutor.execute(new CommunicatingServer(listenForConnection(), printer), threadsNumber);
    }

    private ReadingSocket listenForConnection() {
        return new CommunicatingSocket(socket.acceptConnection());
    }
}

package server.runningServer;

import console.ConsolePrinter;
import server.temporaryServer.CommunicatingServer;
import server.temporaryServer.CommunicatingSocket;
import server.temporaryServer.ReadingSocket;
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

    public void acceptSimultaneousConnections(ServerStatus serverStatus) {
        printer.printServerIsRunning();
        while(serverStatus.isRunning()) {
            threadsExecutor.execute(new CommunicatingServer(listenForConnection(), printer));
        }
    }

    private ReadingSocket listenForConnection() {
        return new CommunicatingSocket(socket.acceptConnection());
    }
}

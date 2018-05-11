package server;

public class EchoServer {

    private final AcceptingSocket socket;

    public EchoServer(AcceptingSocket socket) {
        this.socket = socket;
    }

    public ReadingSocket listenForConnection() {
        return new CommunicatingSocket(socket.acceptConnection());
    }
}

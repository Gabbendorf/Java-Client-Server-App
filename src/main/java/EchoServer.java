public class EchoServer {

    private final AcceptingSocket socket;

    public EchoServer(AcceptingSocket socket) {
        this.socket = socket;
    }

    public ReadingSocket listenForConnection() {
        return new CommunicatingServerSocket(socket.acceptConnection());
    }
}

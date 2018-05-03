import java.net.Socket;

public class ServerSocketSpy implements ServerSideSocket {

    private final int portNumber;
    private boolean wasCalled = false;

    public ServerSocketSpy(int portNumber) {
       this.portNumber = portNumber;
    }

    public boolean isListening() {
        return wasCalled;
    }

    @Override
    public Socket acceptConnection() {
        wasCalled = true;
        return new Socket();
    }
}

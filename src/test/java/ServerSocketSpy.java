import java.net.Socket;

public class ServerSocketSpy implements ServerSideSocket {

    private final int portNumber;
    private boolean wasCalled = false;

    public ServerSocketSpy(int portNumber) {
       this.portNumber = portNumber;
    }

    @Override
    public Socket accept() {
        wasCalled = true;
        return new Socket();
    }

    public boolean isListening() {
        return wasCalled;
    }
}

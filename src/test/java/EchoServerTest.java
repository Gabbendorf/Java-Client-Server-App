import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private ServerSocketDummy socket;

    @Test
    public void listensForConnectionFromClientSocket() {
        socket = new ServerSocketDummy();
        EchoServer server = new EchoServer(socket);

        server.listenForConnection();

        assertTrue(socket.isListening);
    }
}

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private ServerSocketDummy socket;
    private EchoServer server;

    @Before
    public void newServer() {
        socket = new ServerSocketDummy();
        server = new EchoServer(socket);
    }

    @Test
    public void listensForConnectionFromClientSocket() {
        server.listenForConnection();

        assertTrue(socket.isListening);
    }

    @Test
    public void readsMessageFromClient() {
        String userInputToWrite = "hello";
        WritingSocket clientSocket = new ClientSocketSpy(userInputToWrite);

        String messageReceived = server.readMessageFromClient(clientSocket);

        assertEquals(messageReceived, "hello");
    }
}

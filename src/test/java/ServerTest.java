import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerTest {

    private ServerSocketSpy serverSocketDouble;
    private Server server;

    @Before
    public void newServer() {
        serverSocketDouble = new ServerSocketSpy(8080);
        server = new Server(serverSocketDouble);
    }

    @Test
    public void listensForConnectionFromClient() {
        server.listenForConnection();

        assertTrue(serverSocketDouble.isListening());
    }

    @Test
    public void readsTextReceivedFromClient() throws IOException {
        ClientSocketStub clientSocket = new ClientSocketStub();

        String clientText = server.readTextFromClient(clientSocket.inputStream("hello"));

        assertEquals(clientText, "hello");
    }
}

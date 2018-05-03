import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {

    private ClientSocketSpy clientSocket;
    private Client client;

    @Before
    public void newClient() {
        clientSocket = new ClientSocketSpy();
        client = new Client(clientSocket);
    }

    @Test
    public void readsInputFromUser() {
        String userInput = client.readUserInput(input("hello"));

        assertEquals(userInput, "hello");
    }

    @Test
    public void sendsMessageToTheServer() {
        client.sendMessageToServer("hello");

        assertTrue(clientSocket.hasWrittenToOutputStream);
    }

    private InputStream input(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
    }
}

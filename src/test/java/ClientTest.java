import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertTrue;

public class ClientTest {

    private Client client;
    private ClientSocketDouble clientSocket;

    @Before
    public void setUpClient() {
        clientSocket = new ClientSocketDouble("localhost", 8080);
        client = new Client(clientSocket);
    }

    @Test
    public void readsUserInputAndSendsItToServerItConnectsToViaOutputStream() {
        client.connect(input("hello"));

        assertTrue(clientSocket.streamIsWritten);
    }

    private InputStream input(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
    }
}

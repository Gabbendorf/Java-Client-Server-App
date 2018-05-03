import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {

    private ClientSocketSpy clientSocket;
    private Client client;
    private ByteArrayOutputStream output;
    private ConsolePrinter consolePrinter;

    @Before
    public void newClient() {
        output = new ByteArrayOutputStream();
        consolePrinter = new ConsolePrinter(new PrintStream(output));
        clientSocket = new ClientSocketSpy("");
        client = new Client(clientSocket, consolePrinter);
    }

    @Test
    public void printsMessageForSuccessfulConnection() {
        client.printConnectionMessage();

        assertTrue(output.toString().contains("Connected to chat server on port 8080:"));
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

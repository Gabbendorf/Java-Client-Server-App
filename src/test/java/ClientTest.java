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

    private ClientSocketDouble clientSocket;
    private Client client;
    private ByteArrayOutputStream output;
    private ConsolePrinter consolePrinter;
    private ConsoleReader consoleReader;

    @Before
    public void newClient() {
        output = new ByteArrayOutputStream();
        consolePrinter = new ConsolePrinter(new PrintStream(output));
        consoleReader = new ConsoleReader(input("hello"));
        clientSocket = new ClientSocketDouble();
        client = new Client(clientSocket, consolePrinter, consoleReader);
    }

    @Test
    public void printsMessageForSuccessfulConnection() {
        client.printConnectionMessage();

        assertTrue(output.toString().contains("Connected to echo server on port 8080:"));
    }

    @Test
    public void sendsMessageToTheServer() {
        ClientSocketDouble socket = new ClientSocketDouble();
        Client client = newClient(socket,"hello");

        client.sendMessageToServer();

        assertEquals(socket.messageToWrite, "hello");
        assertTrue(socket.hasWrittenToOutputStream);
    }

    private Client newClient(ClientSocketDouble socket, String messageToWrite) {
        consolePrinter = new ConsolePrinter(new PrintStream(new ByteArrayOutputStream()));
        consoleReader = new ConsoleReader(input(messageToWrite));
        return new Client(socket, consolePrinter, consoleReader);
    }

    private InputStream input(String inputToRead) {
        return new ByteArrayInputStream(inputToRead.getBytes(StandardCharsets.UTF_8));
    }
}

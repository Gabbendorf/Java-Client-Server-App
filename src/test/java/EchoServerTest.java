import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private ServerSocketDummy socket;
    private EchoServer server;
    private ByteArrayOutputStream output;
    private ConsolePrinter consolePrinter;

    @Before
    public void newServer() {
        output = new ByteArrayOutputStream();
        consolePrinter = new ConsolePrinter(new PrintStream(output));
        socket = new ServerSocketDummy();
        server = new EchoServer(socket, consolePrinter);
    }

    @Test
    public void printsMessageForSuccessfullyStartedToRun() {
        server.printIsRunningMessage();

        assertTrue(output.toString().contains("Running echo server on port 8080:"));
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

    @Test
    public void printsMessageReceivedFromClientToUser() {
        server.printClientMessage("hello");

        assertTrue(output.toString().contains("hello"));
    }
}

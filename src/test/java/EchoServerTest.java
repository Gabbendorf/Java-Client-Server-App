import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private EchoServer server;
    private ListeningServerSocketDouble acceptingSocket;
    private ByteArrayOutputStream output;

    @Before
    public void newServer() {
        acceptingSocket = new ListeningServerSocketDouble();
        output = new ByteArrayOutputStream();
        server = new EchoServer(acceptingSocket, new ConsolePrinter(new PrintStream(output)));
    }

    @Test
    public void printsMessageForSuccessfulStart() {
        server.printIsRunningMessage();

        assertTrue(output.toString().contains("Running echo server on port 8080:"));
    }

    @Test
    public void listensForConnectionWithClientSocket() {
        server.listenForConnection();

        assertTrue(acceptingSocket.isListening);
    }

    @Test
    public void uponAcceptanceCreatesASocketItUsesToReadFromClientWith() {
        ReadingSocket communicatingSocket = server.listenForConnection();

        assertTrue(communicatingSocket instanceof CommunicatingServerSocket);
    }
}

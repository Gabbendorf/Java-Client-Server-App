import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class CommunicatingServerTest {

    private ByteArrayOutputStream output;
    private CommunicatingServerSocketDouble socket;
    private ConsolePrinter consolePrinter;

    @Before
    public void newServer() {
        output = new ByteArrayOutputStream();
        socket = new CommunicatingServerSocketDouble("hello\nciao\n#quit");
        consolePrinter = new ConsolePrinter(new PrintStream(output));
    }

    @Test
    public void printsMessageForSuccessfulStart() {
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("Running echo server on port 8080:"));
    }

    @Test
    public void printsEachMessageReceivedFromClientToUser() {
        CommunicatingServerSocketDouble socket = new CommunicatingServerSocketDouble("hello\nciao\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("hello\nciao"));
    }

    @Test
    public void stopsRunningIfToldToQuitByClientViaStream() {
        CommunicatingServerSocketDouble socket = new CommunicatingServerSocketDouble("hello\nciao\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(socket.isClosed);
    }
}

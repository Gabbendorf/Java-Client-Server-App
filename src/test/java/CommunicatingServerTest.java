import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class CommunicatingServerTest {

    private CommunicatingServer server;
    private ByteArrayOutputStream output;

    @Before
    public void newServer() {
        output = new ByteArrayOutputStream();
        ReadingSocket communicatingSocket = new CommunicatingServerSocketDouble("hello");
        server = new CommunicatingServer(communicatingSocket, new ConsolePrinter(new PrintStream(output)));
    }

    @Test
    public void printsMessageForSuccessfulStart() {
        server.run();

        assertTrue(output.toString().contains("Running echo server on port 8080:"));
    }

    @Test
    public void printsMessageReceivedFromClientToUser() {
        server.run();

        assertTrue(output.toString().contains("hello"));
    }
}

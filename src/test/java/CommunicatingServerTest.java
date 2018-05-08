import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommunicatingServerTest {

    private CommunicatingServer server;
    private ByteArrayOutputStream output;
    private ReadingSocket communicatingSocket;

    @Before
    public void newServer() {
        output = new ByteArrayOutputStream();
        communicatingSocket = new CommunicatingServerSocketDouble();
        server = new CommunicatingServer(communicatingSocket, new ConsolePrinter(new PrintStream(output)));
    }

    @Test
    public void printsMessageReceivedFromClientToUser() {
        String helloMessage = communicatingSocket.readStream();

        server.printClientMessage();

        assertEquals(helloMessage, "hello");
        assertTrue(output.toString().contains("hello"));
    }
}

package server;

import console.ConsolePrinter;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class CommunicatingServerTest {

    private ByteArrayOutputStream output;
    private FakeCommunicatingServerSocket socket;
    private ConsolePrinter consolePrinter;

    @Before
    public void newServer() {
        output = new ByteArrayOutputStream();
        socket = new FakeCommunicatingServerSocket("hello\nciao\n#quit");
        consolePrinter = new ConsolePrinter(new PrintStream(output));
    }

    @Test
    public void printsMessageForSuccessfulStart() {
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("Running echo server on port 8080:"));
    }

    @Test
    public void printsEachMessageReceived() {
        FakeCommunicatingServerSocket socket = new FakeCommunicatingServerSocket("hello\nciao\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("hello\nciao"));
    }

    @Test
    public void stopsRunningIfToldToQuit() {
        FakeCommunicatingServerSocket socket = new FakeCommunicatingServerSocket("hello\nciao\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(socket.isClosed);
    }
}

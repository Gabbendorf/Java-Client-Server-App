package client;

import console.ConsolePrinter;
import console.FakeConsoleReader;
import console.StreamReader;
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

    private FakeClientSocket socket;
    private Client client;
    private ByteArrayOutputStream output;

    @Before
    public void newClient() {
        output = new ByteArrayOutputStream();
        ConsolePrinter consolePrinter = new ConsolePrinter(new PrintStream(output));
        StreamReader consoleReader = new FakeConsoleReader(input("hello\nhi\n#quit"));
        socket = new FakeClientSocket();
        client = new Client(socket, consolePrinter, consoleReader);
    }

    @Test
    public void printsMessageForSuccessfulConnection() {
        client.run();

        assertTrue(output.toString().contains("Connected to echo server on port 8080:"));
    }

    @Test
    public void keepsWritingMessages() {
        client.run();

        String firstMessageWritten = socket.allMessagesWritten.get(0);
        String secondMessageWritten = socket.allMessagesWritten.get(1);

        assertEquals("hello\n", firstMessageWritten);
        assertEquals("hi\n", secondMessageWritten);
    }

    @Test
    public void writesQuitMessageToServer() {
       client.run();

       String quitMessageWrittenToServer = socket.allMessagesWritten.get(2);

       assertEquals("#quit\n", quitMessageWrittenToServer);
    }

    @Test
    public void closesItsSocketWhenCommandedToQuit() {
        client.run();

        assertTrue(socket.isClosed);
    }

    private InputStream input(String inputToRead) {
        return new ByteArrayInputStream(inputToRead.getBytes(StandardCharsets.UTF_8));
    }
}

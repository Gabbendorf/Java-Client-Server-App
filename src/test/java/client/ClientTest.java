package client;

import console.ConsolePrinter;
import console.ConsoleReader;
import exceptions.ClosingSocketException;
import exceptions.InputStreamException;
import exceptions.OutputStreamException;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {

    private ClientSocketSpy socket;
    private Client client;
    private ByteArrayOutputStream output;
    private ConsolePrinter consolePrinter;
    private ConsoleReader consoleReader;

    @Before
    public void newClient() {
        output = new ByteArrayOutputStream();
        consolePrinter = new ConsolePrinter(new PrintStream(output));
        consoleReader = new ConsoleReader(input("hello\nhi\n#quit"));
        socket = new ClientSocketSpy();
        client = new Client(socket, consolePrinter, consoleReader);
    }

    @Test
    public void printsMessageForSuccessfulConnection() {
        client.connect();

        assertTrue(output.toString().contains("Connected to echo server on port 8080:"));
    }

    @Test(expected = InputStreamException.class)
    public void throwsInputStreamExceptionWhenItCannotGetUserInput() {
        ConsoleReaderWithInputStreamException consoleReader = new ConsoleReaderWithInputStreamException(input("hi\n#quit"));
        Client client = new Client(socket, consolePrinter, consoleReader);

        client.connect();
    }

    @Test
    public void quitsSocketIfGetsNothingAsInput() {
        ConsoleReader consoleReader = new ConsoleReader(input(""));
        Client client = new Client(socket, consolePrinter, consoleReader);

        client.connect();

        assertEquals(consoleReader.readUserInput(), "#quit");
        assertTrue(socket.isClosed);
    }

    @Test(expected = OutputStreamException.class)
    public void throwsOutputStreamExceptionWhenItCannotGetOutputStream() {
        ClientSocketWithOutputStreamException socket = new ClientSocketWithOutputStreamException();
        Client client = new Client(socket, consolePrinter, consoleReader);

        client.connect();
    }

    @Test
    public void keepsWritingMessages() {
        client.connect();

        String firstMessageWritten = socket.allMessagesWritten.get(0);
        String secondMessageWritten = socket.allMessagesWritten.get(1);

        assertEquals("hello", firstMessageWritten);
        assertEquals("hi", secondMessageWritten);
    }

    @Test
    public void writesQuitMessageToServer() {
       client.connect();

       String quitMessageWrittenToServer = socket.allMessagesWritten.get(2);

       assertEquals("#quit", quitMessageWrittenToServer);
    }

    @Test(expected = ClosingSocketException.class)
    public void throwsClosingSocketExceptionWhenItCannotCloseSocket() {
        ClientSocketWithClosingSocketException socket = new ClientSocketWithClosingSocketException();
        Client client = new Client(socket, consolePrinter, consoleReader);

        client.connect();
    }

    @Test
    public void closesItsSocketWhenCommandedToQuit() {
        client.connect();

        assertTrue(socket.isClosed);
    }

    private InputStream input(String inputToRead) {
        return new ByteArrayInputStream(inputToRead.getBytes(StandardCharsets.UTF_8));
    }

    private class ClientSocketWithOutputStreamException extends ClientSocketSpy {

        @Override
        public void writeToStream(String userInput) {
            throw new OutputStreamException(new IOException());
        }
    }

    private class ClientSocketWithClosingSocketException extends ClientSocketSpy {

        @Override
        public void close() {
            throw new ClosingSocketException(new IOException());
        }
    }

    private class ConsoleReaderWithInputStreamException extends ConsoleReader {

        ConsoleReaderWithInputStreamException(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public String readUserInput() {
            throw new InputStreamException(new IOException());
        }
    }
}

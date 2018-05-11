package server;

import console.ConsolePrinter;
import exceptions.ClosingSocketException;
import exceptions.InputStreamException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class CommunicatingServerTest {

    private ByteArrayOutputStream output;
    private FakeCommunicatingSocket socket;
    private ConsolePrinter consolePrinter;

    @Before
    public void newServer() {
        output = new ByteArrayOutputStream();
        socket = new FakeCommunicatingSocket("hello\nciao\n#quit");
        consolePrinter = new ConsolePrinter(new PrintStream(output));
    }

    @Test
    public void printsMessageForSuccessfulStart() {
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("Running echo server on port 8080:"));
    }

    @Test(expected = InputStreamException.class)
    public void throwsInputStreamExceptionWhenItCannotReadStream() {
        SocketWithInputStreamException socket = new SocketWithInputStreamException("hello\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();
    }

    @Test
    public void printsEachMessageReceived() {
        FakeCommunicatingSocket socket = new FakeCommunicatingSocket("hello\nciao\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("hello\nciao"));
    }

    @Test(expected = ClosingSocketException.class)
    public void throwsClosingSocketExceptionWhenItCannotCloseSocket() {
        SocketWithClosingSocketException socket = new SocketWithClosingSocketException("hello\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();
    }

    @Test
    public void stopsRunningIfToldToQuit() {
        FakeCommunicatingSocket socket = new FakeCommunicatingSocket("hello\nciao\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(socket.isClosed);
    }

    private class SocketWithClosingSocketException extends FakeCommunicatingSocket {

        public SocketWithClosingSocketException(String streamFromClient) {
            super(streamFromClient);
        }

        @Override
        public void close() {
            throw new ClosingSocketException("message");
        }
    }

    private class SocketWithInputStreamException extends FakeCommunicatingSocket {

        public SocketWithInputStreamException(String streamFromClient) {
            super(streamFromClient);
        }

        @Override
        public String readStream() {
            throw new InputStreamException("message");
        }
    }
}

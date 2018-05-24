package server.temporaryServer;

import console.ConsolePrinter;
import exceptions.ClosingSocketException;
import exceptions.InputStreamException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class CommunicatingServerTest {

    private ByteArrayOutputStream output;
    private FakeCommunicatingSocket socket;
    private ConsolePrinter consolePrinter;

    @Before
    public void newServer() {
        output = new ByteArrayOutputStream();
        socket = new FakeCommunicatingSocket("gabi\nhello\nciao\n#quit");
        consolePrinter = new ConsolePrinter(new PrintStream(output));
    }

    @Test(expected = InputStreamException.class)
    public void throwsInputStreamExceptionWhenItCannotReadStream() {
        SocketWithInputStreamException socket = new SocketWithInputStreamException("hello\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();
    }

    @Test
    public void confirmsConnectionIsMade() {
        FakeCommunicatingSocket socket = new FakeCommunicatingSocket("gabi\nhello\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("[gabi has connected]"));
    }

    @Test
    public void firstMessageReceivedIsClientName() {
        FakeCommunicatingSocket socket = new FakeCommunicatingSocket("gabi\nhello\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("[from gabi] hello"));
    }

    @Test
    public void printsEachMessageReceived() {
        FakeCommunicatingSocket socket = new FakeCommunicatingSocket("gabi\nciao\nhello\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("[from gabi] ciao\n[from gabi] hello"));
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

    @Test
    public void printsClientLeftMessageWhenQuits() {
        FakeCommunicatingSocket socket = new FakeCommunicatingSocket("gabi\nciao\n#quit");
        CommunicatingServer server = new CommunicatingServer(socket, consolePrinter);

        server.run();

        assertTrue(output.toString().contains("[gabi has left]"));
    }

    private class SocketWithClosingSocketException extends FakeCommunicatingSocket {

        public SocketWithClosingSocketException(String streamFromClient) {
            super(streamFromClient);
        }

        @Override
        public void close() {
            throw new ClosingSocketException(new IOException());
        }
    }

    private class SocketWithInputStreamException extends FakeCommunicatingSocket {

        public SocketWithInputStreamException(String streamFromClient) {
            super(streamFromClient);
        }

        @Override
        public String readStream() {
            throw new InputStreamException(new IOException());
        }
    }
}

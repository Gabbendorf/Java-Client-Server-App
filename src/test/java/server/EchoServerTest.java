package server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private EchoServer server;
    private ListeningServerSocketSpy acceptingSocket;

    @Before
    public void newServer() {
        acceptingSocket = new ListeningServerSocketSpy();
        server = new EchoServer(acceptingSocket);
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

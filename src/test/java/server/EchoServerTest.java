package server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private EchoServer server;
    private ListeningSocketSpy acceptingSocket;

    @Before
    public void newServer() {
        acceptingSocket = new ListeningSocketSpy();
        server = new EchoServer(acceptingSocket);
    }

    @Test
    public void listensForConnection() {
        server.listenForConnection();

        assertTrue(acceptingSocket.isListening);
    }

    @Test
    public void uponAcceptanceCreatesASocket() {
        ReadingSocket communicatingSocket = server.listenForConnection();

        assertTrue(communicatingSocket instanceof CommunicatingSocket);
    }
}

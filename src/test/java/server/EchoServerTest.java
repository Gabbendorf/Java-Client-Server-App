package server;

import exceptions.ConnectionException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private EchoServer server;
    private ListeningSocketSpy acceptingSocket;

    @Before
    public void newServer() {
        acceptingSocket = new ListeningSocketSpy();
        server = new EchoServer(acceptingSocket);
    }

    @Test(expected = ConnectionException.class)
    public void throwsAcceptingSocketExceptionWhenItCannotAcceptConnection() {
        SocketWithAcceptingSocketException socket = new SocketWithAcceptingSocketException();
        EchoServer server = new EchoServer(socket);

        server.listenForConnection();
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

    private class SocketWithAcceptingSocketException extends ListeningSocketSpy {

       @Override
       public Socket acceptConnection(){
            throw new ConnectionException(new IOException());
       }
    }
}

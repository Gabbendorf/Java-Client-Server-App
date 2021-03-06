package server.runningServer;

import console.ConsolePrinter;
import exceptions.ConnectionException;
import org.junit.Before;
import org.junit.Test;
import server.temporaryServer.CommunicatingServer;
import threads.ThreadsExecutorSpy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private EchoServer server;
    private ConsolePrinter printer;
    private ThreadsExecutorSpy threadsExecutor;
    private ByteArrayOutputStream output;

    @Before
    public void newServer() {
        ListeningSocketSpy acceptingSocket = new ListeningSocketSpy();
        output = new ByteArrayOutputStream();
        printer = new ConsolePrinter(new PrintStream(output));
        threadsExecutor = new ThreadsExecutorSpy(printer);
        server = new EchoServer(acceptingSocket, printer, threadsExecutor);
    }

    @Test(expected = ConnectionException.class)
    public void throwsAcceptingSocketExceptionWhenItCannotAcceptConnection() {
        SocketWithAcceptingSocketException socket = new SocketWithAcceptingSocketException();
        EchoServer server = new EchoServer(socket, printer, threadsExecutor);

        server.acceptSimultaneousConnections(new ServerStatus());
    }

    @Test
    public void printsMessageForSuccessfulStart() {
        server.acceptSimultaneousConnections(new ServerStatusStub());

        assertTrue(output.toString().contains("Running echo server on port 8080:"));
    }

    @Test
    public void createsCommunicatingServerForConnectionMade() {
        server.acceptSimultaneousConnections(new ServerStatusStub());

        Runnable communicatingServerRun = threadsExecutor.serversConnected.get(0);

        assertTrue(communicatingServerRun instanceof CommunicatingServer);
    }

    @Test
    public void startsMultipleConnectionsSimultaneously() {
        server.acceptSimultaneousConnections(new ServerStatusStub());

        assertTrue(serversConnectedAreMoreThanOne());
    }

    private boolean serversConnectedAreMoreThanOne() {
        return threadsExecutor.serversConnected.size() > 1;
    }

    private class SocketWithAcceptingSocketException extends ListeningSocketSpy {

       @Override
       public Socket acceptConnection(){
            throw new ConnectionException(new IOException());
       }
    }

    private class ServerStatusStub extends ServerStatus {

        private int connectionsMade = 0;

        @Override
        public boolean isRunning() {
            while (connectionsMade < 3) {
                connectionsMade += 1;
                return true;
            }
            return false;
        }
    }
}

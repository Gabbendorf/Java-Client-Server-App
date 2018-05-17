package server;

import console.ConsolePrinter;
import exceptions.ConnectionException;
import org.junit.Before;
import org.junit.Test;
import threads.MultiConnectionsExecutor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private EchoServer server;
    private ConsolePrinter printer;
    private ThreadsExecutorSpy threadsExecutor;

    @Before
    public void newServer() {
        ListeningSocketSpy acceptingSocket = new ListeningSocketSpy();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        printer = new ConsolePrinter(new PrintStream(output));
        threadsExecutor = new ThreadsExecutorSpy();
        server = new EchoServer(acceptingSocket, printer, threadsExecutor);
    }

    @Test(expected = ConnectionException.class)
    public void throwsAcceptingSocketExceptionWhenItCannotAcceptConnection() {
        SocketWithAcceptingSocketException socket = new SocketWithAcceptingSocketException();
        EchoServer server = new EchoServer(socket, printer, threadsExecutor);

        server.acceptSimultaneousConnectionsUpTo(1);
    }

    @Test
    public void runsCommunicatingServerForConnectionMade() {
        server.acceptSimultaneousConnectionsUpTo(1);

        Runnable communicatingServerRun = threadsExecutor.serversConnected.get(0);

        assertTrue(communicatingServerRun instanceof CommunicatingServer);
    }

    @Test
    public void startsThreeConnectionsSimultaneously() {
        server.acceptSimultaneousConnectionsUpTo(3);

        int serversConnectedNumber = threadsExecutor.serversConnected.size();

        assertEquals(3, serversConnectedNumber);
    }

    private class SocketWithAcceptingSocketException extends ListeningSocketSpy {

       @Override
       public Socket acceptConnection(){
            throw new ConnectionException(new IOException());
       }
    }

    private class ThreadsExecutorSpy implements MultiConnectionsExecutor {

        List<CommunicatingServer> serversConnected = new ArrayList<>();

        @Override
        public void execute(CommunicatingServer server, int threadsNumber) {
            int i = 0;
            while(i != threadsNumber) {
                serversConnected.add(new CommunicatingServer(new FakeCommunicatingSocket("hi\n#quit"), printer));
                i += 1;
            }
        }
    }
}

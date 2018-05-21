package threads;

import console.ConsolePrinter;
import server.CommunicatingServer;
import server.FakeCommunicatingSocket;

import java.util.ArrayList;
import java.util.List;

public class ThreadsExecutorSpy implements  MultiConnectionsExecutor {

    private final ConsolePrinter printer;
    public List<CommunicatingServer> serversConnected;

        public ThreadsExecutorSpy(ConsolePrinter printer) {
            this.serversConnected = new ArrayList<>();
            this.printer = printer;
        }

        @Override
        public void execute(CommunicatingServer server) {
            serversConnected.add(new CommunicatingServer(new FakeCommunicatingSocket("hi\n#quit"), printer));
        }
}

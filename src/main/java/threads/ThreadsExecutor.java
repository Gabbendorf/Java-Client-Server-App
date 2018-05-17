package threads;

import server.CommunicatingServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsExecutor implements MultiConnectionsExecutor {

    @Override
    public void execute(CommunicatingServer communicatingServer, int threadsNumber) {
        ExecutorService threadPool = Executors.newFixedThreadPool(threadsNumber);
        threadPool.execute(communicatingServer);
    }
}

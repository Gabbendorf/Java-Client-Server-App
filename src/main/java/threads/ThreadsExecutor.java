package threads;

import server.CommunicatingServer;

import java.util.concurrent.ExecutorService;

public class ThreadsExecutor implements MultiConnectionsExecutor {

    private final ExecutorService threadPool;

    public ThreadsExecutor(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public void execute(CommunicatingServer communicatingServer) {
        threadPool.execute(communicatingServer);
    }
}

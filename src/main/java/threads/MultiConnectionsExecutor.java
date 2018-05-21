package threads;

import server.CommunicatingServer;

public interface MultiConnectionsExecutor {
    void execute(CommunicatingServer server);
}

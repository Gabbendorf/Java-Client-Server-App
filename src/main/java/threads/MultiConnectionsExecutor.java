package threads;

import server.temporaryServer.CommunicatingServer;

public interface MultiConnectionsExecutor {
    void execute(CommunicatingServer server);
}

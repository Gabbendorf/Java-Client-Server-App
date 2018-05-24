package server.runningServer;

import java.net.Socket;

public class ListeningSocketSpy implements AcceptingSocket {

    boolean isListening;

    @Override
    public Socket acceptConnection() {
        isListening = true;
        return new Socket();
    }
}

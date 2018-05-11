package server;

import java.net.Socket;

public class ListeningServerSocketSpy implements AcceptingSocket {

    public boolean isListening;

    @Override
    public Socket acceptConnection() {
        isListening = true;
        return new Socket();
    }
}

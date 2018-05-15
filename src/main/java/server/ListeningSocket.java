package server;

import exceptions.ConnectionException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListeningSocket implements AcceptingSocket {

    private final ServerSocket serverSocket;

    public ListeningSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket acceptConnection() {
        Socket socket;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
        return socket;
    }
}

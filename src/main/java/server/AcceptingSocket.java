package server;

import java.net.Socket;

public interface AcceptingSocket {

    Socket acceptConnection();
}

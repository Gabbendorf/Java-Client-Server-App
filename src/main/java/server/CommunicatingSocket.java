package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommunicatingSocket implements ReadingSocket {

    private final Socket socket;

    public CommunicatingSocket(Socket socket) {
        this.socket = socket;
    }

    private BufferedReader getStreamFromClient() {
        InputStreamReader streamReader = null;
        try {
            streamReader = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BufferedReader(streamReader);

    }

    @Override
    public String readStream() {
        String clientMessage = "";
        try {
            clientMessage = getStreamFromClient().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientMessage;
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

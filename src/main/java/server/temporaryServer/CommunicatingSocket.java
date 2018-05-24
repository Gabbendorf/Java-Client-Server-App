package server.temporaryServer;

import exceptions.ClosingSocketException;
import exceptions.InputStreamException;

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
        InputStreamReader streamReader;
        try {
            streamReader = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            throw new InputStreamException(e);
        }
        return new BufferedReader(streamReader);

    }

    @Override
    public String readStream() {
        String clientMessage;
        try {
            clientMessage = getStreamFromClient().readLine();
        } catch (IOException e) {
            throw new InputStreamException(e);
        }
        return clientMessage;
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new ClosingSocketException(e);
        }
    }
}

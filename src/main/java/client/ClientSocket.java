package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket implements WritingSocket {

    private final Socket socket;

    public ClientSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void writeToStream(String userInput) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(userInput);
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSideSocket  implements WritingSocket {

    private final Socket socket;

    public ClientSideSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void writeToStream(String userInput) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(userInput);
    }
}

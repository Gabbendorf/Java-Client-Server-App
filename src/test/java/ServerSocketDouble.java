import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ServerSocketDouble implements ReadingSocket {

    public boolean isListening = false;

    @Override
    public Socket acceptConnection() {
        isListening = true;
        return new Socket();
    }

    @Override
    public String readStream(WritingSocket clientSocket) {
        Scanner scanner = new Scanner(clientSocket.writtenStream(), StandardCharsets.UTF_8.name());
        return scanner.nextLine();
    }
}

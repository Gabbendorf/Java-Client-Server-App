import java.io.*;

public class Client {

    private final WritingSocket socket;

    public Client(WritingSocket socket) {
        this.socket = socket;
    }

    private BufferedReader userInput(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public String readUserInput(InputStream inputStream) {
        String userInput = "";
        try {
            userInput = userInput(inputStream).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }

    public void sendMessageToServer(String userInput) {
        socket.writeToStream(userInput);
    }
}

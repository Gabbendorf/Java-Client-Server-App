import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Client {

    private final ClientSideSocket socket;

    public Client(ClientSideSocket socket) {
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
}

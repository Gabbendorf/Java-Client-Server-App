import java.io.*;

public class Client {

    private final ClientSideSocket socket;

    public Client(ClientSideSocket socket) {
        this.socket = socket;
    }

    public void connect(InputStream input) {
        System.out.println("--- Connected echo server on port 8080 ---");
        String userInput = readUserInput(input);
        sendTextToServer(userInput);
    }

    private BufferedReader saveInputFromUser(InputStream input) {
       return new BufferedReader(new InputStreamReader(input));
    }

    private String readUserInput(InputStream input) {
        String userInput = "";
        try {
             userInput = saveInputFromUser(input).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }

    private void sendTextToServer(String userInput) {
        PrintWriter writer = new PrintWriter(socket.writeStream(), true);
        writer.println(userInput);
    }
}

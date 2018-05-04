import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleReader {

    private final InputStream input;

    public ConsoleReader(InputStream input) {
        this.input = input;
    }

    public String readInput(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String userInput = "";
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }
}

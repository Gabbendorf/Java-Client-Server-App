package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleReader implements StreamReader {

    private final InputStream input;

    public ConsoleReader(InputStream input) {
        this.input = input;
    }

    public String readUserInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        String userInput = "";
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }
}

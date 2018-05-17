package console;

import exceptions.InputStreamException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleReader {

    private final BufferedReader bufferedReader;

    public ConsoleReader(InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String readUserInput() {
        String userInput;
        try {
            userInput = bufferedReader.readLine();
            if (userInput == null) {
                userInput = "#quit";
            }
        } catch (IOException e) {
            throw new InputStreamException(e);
        }
        return userInput;
    }
}

package console;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReaderDouble implements StreamReader {

    private final Scanner input;

    public ConsoleReaderDouble(InputStream inputStream) {
        this.input = new Scanner(inputStream);
    }

    @Override
    public String readUserInput() {
        return input.nextLine();
    }
}

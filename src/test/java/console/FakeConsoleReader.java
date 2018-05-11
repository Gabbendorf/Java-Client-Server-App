package console;

import java.io.InputStream;
import java.util.Scanner;

public class FakeConsoleReader implements StreamReader {

    private final Scanner input;

    public FakeConsoleReader(InputStream inputStream) {
        this.input = new Scanner(inputStream);
    }

    @Override
    public String readUserInput() {
        return input.nextLine();
    }
}

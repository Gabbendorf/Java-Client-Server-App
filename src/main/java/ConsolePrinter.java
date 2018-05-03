import java.io.PrintStream;

public class ConsolePrinter {

    private final PrintStream output;

    public ConsolePrinter(PrintStream output) {
        this.output = output;
    }

    public void connectionMessage() {
        output.println("Connected to chat server on port 8080:");
    }
}

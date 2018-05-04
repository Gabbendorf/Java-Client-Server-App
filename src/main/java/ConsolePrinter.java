import java.io.PrintStream;

public class ConsolePrinter {

    private final PrintStream output;

    public ConsolePrinter(PrintStream output) {
        this.output = output;
    }

    public void connectionMessage() {
        output.println("Connected to echo server on port 8080:");
    }

    public void runningMessage() {
        output.println("Running echo server on port 8080:");
    }

    public void printMessageFromClient(String clientMessage) {
        output.println(clientMessage);
    }
}

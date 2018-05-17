package console;

import java.io.PrintStream;

public class ConsolePrinter {

    private final PrintStream output;

    public ConsolePrinter(PrintStream output) {
        this.output = output;
    }

    public void printClientHasConnectedToServer() {
        output.println("Connected to echo server on port 8080:");
    }

    public void printServerIsRunning() {
        output.println("Running echo server on port 8080:");
    }

    public void printMessageFromClient(String clientMessage) {
        output.println(clientMessage);
    }
}

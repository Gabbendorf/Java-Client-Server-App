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

    public void printMessageFromClient(String clientName, String clientMessage) {
        output.println(String.format("[from %s] %s", clientName, clientMessage));
    }

    public void clientLeftMessage(String clientName) {
        output.println(String.format("[%s has left]", clientName));
    }

    public void confirmConnectionWithClient(String clientName) {
        output.println(String.format("[%s has connected]", clientName));
    }
}

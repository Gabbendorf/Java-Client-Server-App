package exceptions;

public class OutputStreamException extends RuntimeException {

    public OutputStreamException(String message) {
        super("Could not print stream: " + message);
    }
}

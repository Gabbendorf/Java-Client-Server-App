package exceptions;

public class InputStreamException extends RuntimeException {

    public InputStreamException(String message) {
        super("Could not read stream: " + message);
    }
}

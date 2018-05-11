package exceptions;

public class ClosingSocketException extends RuntimeException {

    public ClosingSocketException(String message) {
        super("Socket could not close: " + message);
    }
}

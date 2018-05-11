package exceptions;

public class ConnectionException extends RuntimeException {

    public ConnectionException(String message) {
        super("Could not connect: " + message);
    }
}

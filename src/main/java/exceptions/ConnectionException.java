package exceptions;

import java.io.IOException;

public class ConnectionException extends RuntimeException {

    public ConnectionException(IOException e) {
        super("Could not connect: " + e.getMessage(), e);
    }
}

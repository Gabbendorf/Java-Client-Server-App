package exceptions;

import java.io.IOException;

public class ClosingSocketException extends RuntimeException {

    public ClosingSocketException(IOException e) {
        super("Socket could not close " + e.getMessage(), e);
    }
}

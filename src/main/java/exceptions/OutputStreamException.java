package exceptions;

import java.io.IOException;

public class OutputStreamException extends RuntimeException {

    public OutputStreamException(IOException e) {
        super("Could not print stream: " + e.getMessage(), e);
    }
}

package exceptions;

import java.io.IOException;

public class InputStreamException extends RuntimeException {

    public InputStreamException(IOException e) {
        super("Could not read stream: " + e.getMessage(), e);
    }
}

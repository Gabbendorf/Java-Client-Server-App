package server;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FakeCommunicatingSocket implements ReadingSocket {

    private final BufferedReader bufferedReader;
    boolean isClosed;

    public FakeCommunicatingSocket(String streamFromClient) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream(streamFromClient)));
    }

    @Override
    public String readStream() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        isClosed = true;
    }

    private InputStream inputStream(String inputToRead) {
        return new ByteArrayInputStream(inputToRead.getBytes(StandardCharsets.UTF_8));
    }
}

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    @Test
    public void readsInputFromUser() {
        Client client = new Client(new ClientSocketDummy());

        String userInput = client.readUserInput(input("hello"));

        assertEquals(userInput, "hello");
    }

    private InputStream input(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
    }
}

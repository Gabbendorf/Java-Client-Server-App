public class ClientSocketDouble implements WritingSocket {

    private final String userInput;
    public boolean hasWrittenToOutputStream;

    public ClientSocketDouble(String userInput) {
        this.hasWrittenToOutputStream = false;
        this.userInput = userInput;
    }

    @Override
    public void writeToStream(String userInput) {
        hasWrittenToOutputStream = true;
    }
}

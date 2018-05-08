public class ClientSocketDouble implements WritingSocket {

    public boolean hasWrittenToOutputStream;
    public String messageToWrite;

    public ClientSocketDouble() {
        this.hasWrittenToOutputStream = false;
    }

    @Override
    public void writeToStream(String userInput) {
        messageToWrite = userInput;
        hasWrittenToOutputStream = true;
    }
}

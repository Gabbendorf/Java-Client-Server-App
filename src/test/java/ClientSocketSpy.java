public class ClientSocketSpy implements WritingSocket {

    public boolean hasWrittenToOutputStream;

    public ClientSocketSpy() {
        this.hasWrittenToOutputStream = false;
    }

    @Override
    public void writeToStream(String userInput) {
        hasWrittenToOutputStream = true;
    }
}

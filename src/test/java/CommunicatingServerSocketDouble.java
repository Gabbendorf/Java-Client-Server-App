public class CommunicatingServerSocketDouble implements ReadingSocket {

    @Override
    public String readStream() {
        return "hello";
    }
}

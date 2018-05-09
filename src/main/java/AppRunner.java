import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppRunner {

    private static int portNumber = 8080;

    public static void main(String[] args) {
        ConsolePrinter printer = new ConsolePrinter(System.out);
        ConsoleReader reader = new ConsoleReader(System.in);
        String mode = args[0];
        if (mode.equals("server")) {
            try {
                AcceptingSocket listeningSocket = new ListeningServerSocket(new ServerSocket(portNumber));
                EchoServer echoServer = new EchoServer(listeningSocket);
                CommunicatingServer communicatingServer = new CommunicatingServer(echoServer.listenForConnection(), printer);

                communicatingServer.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Socket socket = new Socket("localhost", portNumber);
                Client client = new Client(new ClientSocket(socket), printer, reader);

                client.run();
            } catch (ConnectException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

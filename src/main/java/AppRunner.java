import client.Client;
import client.ClientSocket;
import console.ConsolePrinter;
import console.ConsoleReader;
import exceptions.ConnectionException;
import server.AcceptingSocket;
import server.CommunicatingServer;
import server.EchoServer;
import server.ListeningSocket;

import java.io.IOException;
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
                AcceptingSocket listeningSocket = new ListeningSocket(new ServerSocket(portNumber));
                EchoServer echoServer = new EchoServer(listeningSocket);
                CommunicatingServer communicatingServer = new CommunicatingServer(echoServer.listenForConnection(), printer);

                communicatingServer.run();
            } catch (IOException e) {
                throw new ConnectionException(e);
            }
        } else {
            try {
                Socket socket = new Socket("localhost", portNumber);
                Client client = new Client(new ClientSocket(socket), printer, reader);

                client.run();
            } catch (IOException e) {
                throw new ConnectionException(e);
            }
        }
    }
}

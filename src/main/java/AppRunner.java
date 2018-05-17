import client.Client;
import client.ClientSocket;
import console.ConsolePrinter;
import console.ConsoleReader;
import exceptions.ConnectionException;
import server.*;
import threads.ThreadsExecutor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppRunner {

    private static int portNumber = 8080;

    public static void main(String[] args) throws ConnectionException, IOException {
        String mode = args[0];
        ConsolePrinter printer = new ConsolePrinter(System.out);
        ConsoleReader reader = new ConsoleReader(System.in);

        if (mode.equals("server")) {
            AcceptingSocket listeningSocket = new ListeningSocket(new ServerSocket(portNumber));
            EchoServer echoServer = new EchoServer(listeningSocket, printer, new ThreadsExecutor());
            while(true) {
                echoServer.acceptSimultaneousConnectionsUpTo(5);
            }
        } else {
            Socket socket = new Socket("localhost", portNumber);
            Client client = new Client(new ClientSocket(socket), printer, reader);

            client.connect();
        }
    }
}

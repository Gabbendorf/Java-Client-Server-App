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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppRunner {

    private static int portNumber = 8080;

    public static void main(String[] args) throws ConnectionException, IOException {
        String mode = args[0];
        ConsolePrinter printer = new ConsolePrinter(System.out);
        ConsoleReader reader = new ConsoleReader(System.in);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        if (mode.equals("server")) {
            AcceptingSocket listeningSocket = new ListeningSocket(new ServerSocket(portNumber));
            EchoServer echoServer = new EchoServer(listeningSocket, printer, new ThreadsExecutor(threadPool));

            echoServer.acceptSimultaneousConnections(new ServerStatus());
        } else {
            Socket socket = new Socket("localhost", portNumber);
            Client client = new Client(new ClientSocket(socket), printer, reader);

            client.connect();
        }
    }
}

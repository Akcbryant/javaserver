package javaserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private boolean serverIsOn = false;
    private Router router;
    private Authenticator authenticator;
    private ExecutorService threadPool = Executors.newFixedThreadPool(100);

    Server() {

    }

    public Server(Router router, Authenticator authenticator, int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        this.router = router;
        this.authenticator = authenticator;
    }

    Server(Router router, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.router = router;
    }

    public void turnOn() {
        try {
            serverIsOn = true;
            while (serverIsOn) {
                clientSocket = serverSocket.accept();
                Runnable worker = new Worker(clientSocket, router, authenticator);
                threadPool.execute(worker);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public boolean isOn() {
        return serverIsOn;
    }

    public void turnOff() {
        serverIsOn = false;
    }
}

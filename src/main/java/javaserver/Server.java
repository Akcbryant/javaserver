package javaserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private boolean serverIsOn = false;
    private Router router;

    Server(int port, Router router) {
        this.port = port;
        this.router = router;
    }

    public void turnOn() {
        try {
            listenOnPort();
            while (serverIsOn) {
                clientSocket = serverSocket.accept();
                Runnable worker = new Worker(clientSocket, router);
                new Thread(worker).start();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    protected void listenOnPort() throws IOException {
        serverSocket = new ServerSocket(port);
        serverIsOn = true;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public boolean isOn() {
        return serverIsOn;
    }
}

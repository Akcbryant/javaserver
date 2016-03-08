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

    Server(int port) {
        this.port = port;
    }

    Server(int port, ServerSocket serverSocket) {
        this.port = port;
        this.serverSocket = serverSocket;
    }

    public void turnOn() {
        try {
            listenOnPort();
            while (serverIsOn) {
                clientSocket = serverSocket.accept();
                Runnable worker = new Worker(clientSocket);
                new Thread(worker).start();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private void listenOnPort() throws IOException {
        serverSocket = new ServerSocket(port);
        serverIsOn = true;
    }


    public void turnOff() {
        try {
            clientSocket.close();
            serverSocket.close();
            serverIsOn = false;
        } catch (IOException e) {
            System.out.println("Exception caught when trying to close the Server" + e.toString());
        }
    }
}

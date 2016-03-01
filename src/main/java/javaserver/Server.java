package javaserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port;
    ServerSocket serverSocket;
    Socket clientSocket;
    boolean readyForClient = false;

    Server(int port) {
        this.port = port;
    }

    Server(int port, ServerSocket serverSocket, Socket clientSocket) {
        this.port = port;
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
    }

    public void start() {
        try {
            listenOnPort();
            acceptClient();
            respond();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private void listenOnPort() throws IOException {
        if (serverSocket != null) {
            readyForClient = true;
        } else {
            serverSocket = new ServerSocket(port);
            readyForClient = true;
        }
    }

    private void acceptClient() throws IOException {
        while (readyForClient) {
            if (clientSocket != null) {
                readyForClient = false;
            } else {
                clientSocket = serverSocket.accept();
                readyForClient = false;
            }
        }
    }

    private void respond() throws IOException {
        System.out.println(clientSocket.getInputStream().available());
        String httpOK = "HTTP/1.1 200 OK\r\n\r\n";
        clientSocket.getOutputStream().write(httpOK.getBytes());
    }

    public void turnOff() {
        try {
            serverSocket.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to close the sockets." + e.toString());
        }
    }
}

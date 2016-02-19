package javaserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port;
    ServerSocket serverSocket;
    Socket clientSocket;

    Server(int port) {
        this.port = port;
    }

    public void start() {
        openPort();
        acceptClient();
        respond();
        breakDown();
    }

    private void openPort() {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port."
                 + port);
        }
    }

    private void acceptClient() {
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to accept client." + e.toString());
        }
    }

    private void respond() {
        String httpOK = "HTTP/1.1 200 OK\r\n\r\n";
        try {
            clientSocket.getOutputStream().write(httpOK.getBytes());
        } catch (IOException e) {
            System.out.println("Exception caught when trying to write to client socket." + e.toString());
        }
    }

    private void breakDown() {
        try {
            serverSocket.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to close the sockets." + e.toString());
        }
    }
}

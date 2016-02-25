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
        openPort();
        acceptClient();
        respond();
    }

    private void openPort() {
        try {
            if (serverSocket != null) {
                readyForClient = true;
            } else {
                serverSocket = new ServerSocket(port);
                readyForClient = true;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port."
                 + port);
        }
    }

    private void acceptClient() {
        while (readyForClient) {
            try {
                if (clientSocket != null) {
                    readyForClient = false; 
                } else {
                    clientSocket = serverSocket.accept();
                    readyForClient = false;
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to accept client." + e.toString());
            }
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

    public void turnOff() {
        try {
            readyForClient = false;
            serverSocket.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to close the sockets." + e.toString());
        }
    }
}

package javaserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port;
    ServerSocket serverSocket;
    Socket clientSocket;
    boolean serverIsOn = false;

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
                respond();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private void listenOnPort() throws IOException {
        serverSocket = new ServerSocket(port);
        serverIsOn = true;
    }

    private void respond() throws IOException {
        Request request = new RequestParser().parseRequest(clientSocket.getInputStream());
        String httpOK = "HTTP/1.1 200 OK\r\n\r\n";
        System.out.println("Current Request Method: " + request.getMethod());
        clientSocket.getOutputStream().write(httpOK.getBytes());
        clientSocket.close();
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

package javaserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

public class Worker implements Runnable {

    private Socket clientSocket;

    Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            Request request = new RequestParser().parseRequest(clientSocket.getInputStream());;
            String httpOK = "HTTP/1.1 200 OK\r\n\r\n";
            clientSocket.getOutputStream().write(httpOK.getBytes());
            clientSocket.close();
        } catch (IOException e) {

        }
    }
}

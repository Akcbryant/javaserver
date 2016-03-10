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
            Request request = new RequestParser().parseRequest(clientSocket.getInputStream());
            Response response = new RequestHandler(request).handleRequest();
            clientSocket.getOutputStream().write(response.toString().getBytes());
            clientSocket.close();
        } catch (IOException e) {

        }
    }

    private Response handleRequest(Request request) {
        RequestHandler handler = new RequestHandler(request);
        Response response = handler.handleRequest();
        return response;
    }
}

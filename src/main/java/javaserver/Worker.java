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
            Response response = handleRequest(request);
            clientSocket.getOutputStream().write(response.toString().getBytes("UTF-8"));
            clientSocket.close();
        } catch (IOException e) {

        }
    }

    private Response handleRequest(Request request) {
        Response response = new RequestHandler().handleRequest(request);
        return response;
    }
}

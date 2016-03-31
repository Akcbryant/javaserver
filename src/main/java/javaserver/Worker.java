package javaserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

import javaserver.handlers.Response;

public class Worker implements Runnable {
    private Socket clientSocket;
    private Router router;

    Worker(Socket clientSocket, Router router) {
        this.clientSocket = clientSocket;
        this.router = router;
    }

    public void run() {
        try {
            Request request = getRequest(clientSocket.getInputStream());
            Response response = handleRequest(request);
            writeResponse(response);
        } catch (IOException e) {

        }
    }

    private void writeResponse(Response response) throws IOException {
        clientSocket.getOutputStream().write(response.toString().getBytes("UTF-8"));
        clientSocket.close();
    }

    private Response handleRequest(Request request) {
        Response response = new RequestHandler(router).handleRequest(request);
        return response;
    }

    private Request getRequest(InputStream inputStream) {
        return new RequestParser().parseRequest(inputStream);
    }
}

package javaserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

import javaserver.handlers.Response;

public class Worker implements Runnable {

    private Socket clientSocket;
    private Router router;
    private Authenticator authenticator;

    Worker(Socket clientSocket, Router router, Authenticator authenticator) {
        this.clientSocket = clientSocket;
        this.router = router;
        this.authenticator = authenticator;
    }

    public void run() {
        try {
            Request request = getRequest(clientSocket.getInputStream());
            System.out.println(request.toString());
            Response response = handleRequest(request);
            writeResponse(response);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private void writeResponse(Response response) throws IOException {
        clientSocket.getOutputStream().write(response.getBytes());
        clientSocket.close();
    }

    private Response handleRequest(Request request) {
        Response response = new RequestHandler(router, authenticator).handleRequest(request);
        return response;
    }

    protected Request getRequest(InputStream inputStream) {
        return new RequestParser().parseRequest(inputStream);
    }
}

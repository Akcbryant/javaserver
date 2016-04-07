package javaserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

import javaserver.handlers.Response;
import javaserver.handlers.Status;

public class Worker implements Runnable {

    private Socket clientSocket;
    private Router router;
    private Authenticator authenticator;
    private Response response;

    Worker() {
    }

    Worker(Socket clientSocket, Router router, Authenticator authenticator) {
        this.clientSocket = clientSocket;
        this.router = router;
        this.authenticator = authenticator;
    }

    public void run() {
        try {
            Request request = getRequest(clientSocket.getInputStream());
            response = handleRequest(request);
            System.out.println(response);
            writeResponse(response);
        } catch (IOException e) {
            closeSocket();
        }
    }

    protected Request getRequest(InputStream inputStream) {
        return new RequestParser().parseRequest(inputStream);
    }

    protected void writeResponse(Response response) throws IOException {
        clientSocket.getOutputStream().write(response.getBytes());
        closeSocket();
    }

    protected Response handleRequest(Request request) {
        Response response = new RequestHandler(router, authenticator).handleRequest(request);
        return response;
    }

    protected void closeSocket() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}

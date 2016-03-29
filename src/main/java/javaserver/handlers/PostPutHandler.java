package javaserver.handlers;

import javaserver.Request;

import java.nio.file.Files;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PostPutHandler implements Handler {


    static final Path path = Paths.get("./test");

    public Response handleRequest(Request request) {
        Response response = new Response();
        try {
            writeData(request.getBody());
            response.setStatus(Status.Ok);
        } catch (IOException e) {
            response.setStatus(Status.ServerError);
        }
        return response;
    }

    public void writeData(String dataString) throws IOException {
        byte[] bodyData = dataString.getBytes();
        Files.write(path, bodyData);
    }
}

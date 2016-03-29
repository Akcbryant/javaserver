package javaserver.handlers;

import javaserver.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetHandler implements Handler {

    static final Path path = Paths.get("./test");

    public Response handleRequest(Request request) {
        Response response = new Response();

        try {
            response.setStatus(Status.Ok);
            String dataString = getData();
            response.setBody(dataString);
        } catch (IOException e) {
            response.setStatus(Status.Ok);
        }
        return response;
    }

    public String getData() throws IOException {
        byte[] data = Files.readAllBytes(path);
        return new String(data);
    }
}

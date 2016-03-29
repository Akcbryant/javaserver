package javaserver.handlers;

import javaserver.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteHandler implements Handler {

    static final Path path = Paths.get("./test");

    public Response handleRequest(Request request) {
        Response response = new Response();
        try {
            deleteFile(path);
            response.setStatus(Status.Ok);
        } catch (IOException e) {
            response.setStatus(Status.ServerError);
        }
        return response;
    }

    public void deleteFile(Path path) throws IOException {
        Files.delete(path);
    }
}

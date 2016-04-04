package javaserver.handlers;

import javaserver.Request;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class PostPutHandler implements Handler {

    private String fileUri;

    public PostPutHandler(String fileUri) {
        this.fileUri = fileUri;
    }

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
        File file = new File(fileUri);
        Files.write(file.toPath(), dataString.getBytes());
    }
}

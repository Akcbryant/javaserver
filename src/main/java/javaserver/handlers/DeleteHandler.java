package javaserver.handlers;

import javaserver.Request;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteHandler implements Handler {

    String fileUri;

    public DeleteHandler(String fileUri) {
        this.fileUri = fileUri;
    }

    public Response handleRequest(Request request) {
        Response response = new Response();
        try {
            deleteFile();
            response.setStatus(Status.Ok);
        } catch (IOException e) {
            response.setStatus(Status.ServerError);
        }
        return response;
    }

    public void deleteFile() throws IOException {
        File file = new File(fileUri);
        Files.delete(file.toPath());
    }
}

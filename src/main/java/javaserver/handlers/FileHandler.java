package javaserver.handlers;

import javaserver.Request;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class FileHandler implements Handler {

    private String fileUri;
    private Response response = new Response();

    public FileHandler(String fileUri) {
        this.fileUri = fileUri;
    }

    public Response handleRequest(Request request) {
        response = new Response();

        String method = request.getMethod();
        if (!method.equals("GET")) {
            response.setStatus(Status.MethodNotAllowed);
            return response;
        }

        String body = getFileContents(fileUri);
        response.setBody(body);

        return response;
    }

    public String getFileContents(String fileUri) {
        File file = new File(fileUri);

        String contents = "";

        try {
            contents = new String(Files.readAllBytes(file.toPath()));
            response.setStatus(Status.Ok);
            return contents;
        } catch (IOException e) {
            response.setStatus(Status.ServerError);
            return contents;
        }
    }
}

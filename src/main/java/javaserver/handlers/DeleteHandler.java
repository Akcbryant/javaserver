package javaserver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class DeleteHandler extends Handler {

    public Response handleRequest(Request request) {
        try {
            deleteFile(path);
        } catch (IOException e) {
            response = new ResponseBuilder().buildFailedResponse();
        }
        return response;
    }

    public void deleteFile(Path path) throws IOException {
        Files.delete(path);
    }
}

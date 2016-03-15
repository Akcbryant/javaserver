package javaserver;

import java.nio.file.Files;
import java.io.IOException;

public class Delete extends Handler {

    public Response handleRequest(Request request) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            response = new ResponseBuilder().buildFailedResponse();
            System.out.println(e.toString());
        }
        return response;
    }
}

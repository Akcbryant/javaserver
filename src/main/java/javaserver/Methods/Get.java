package javaserver;

import java.util.Arrays;

import java.nio.file.Files;
import java.io.IOException;

public class Get extends Handler {

    public Response handleRequest(Request request) {
        if (allowedPaths.contains(request.getPath())) {
            try {
                byte[] data = Files.readAllBytes(path);
                String dataString = new String(data);
                response.setBody(dataString);
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        } else {
            response = new ResponseBuilder().buildFailedResponse();
        }
        return response;
    }
}

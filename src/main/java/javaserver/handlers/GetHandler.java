package javaserver;

import java.util.Arrays;

import java.nio.file.Files;
import java.io.IOException;

public class GetHandler extends Handler {

    public Response handleRequest(Request request) {
        if (allowedPaths.contains(request.getPath())) {
            try {
                String dataString = getData();
                response.setBody(dataString);
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        } else {
            response = new ResponseBuilder().buildFailedResponse();
        }
        return response;
    }

    public String getData() throws IOException {
        byte[] data = Files.readAllBytes(path);
        return new String(data);
    }
}

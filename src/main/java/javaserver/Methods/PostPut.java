package javaserver;

import java.nio.file.Files;
import java.io.IOException;

public class PostPut extends Handler {


    public Response handleRequest(Request request) {
        try {
            byte[] bodyData = request.getBody().getBytes();
            Files.write(path, bodyData);
        } catch (IOException e) {
            response = new ResponseBuilder().buildFailedResponse();
            System.out.println(e.toString());
        }
        return response;
    }
}

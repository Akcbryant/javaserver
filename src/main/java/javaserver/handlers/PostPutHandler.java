package javaserver;

import java.nio.file.Files;
import java.io.IOException;

public class PostPutHandler extends Handler {


    public Response handleRequest(Request request) {
        try {
            writeData(request.getBody());
        } catch (IOException e) {
            response = new ResponseBuilder().buildResponse(Status.NotFound);
        }
        return response;
    }

    public void writeData(String dataString) throws IOException {
        byte[] bodyData = dataString.getBytes();
        Files.write(path, bodyData);
    }
}

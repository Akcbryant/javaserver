package javaserver;

import java.util.Arrays;

import java.nio.file.Files;
import java.io.IOException;

public class GetHandler extends Handler {

    public Response handleRequest(Request request) {
        try {
            String dataString = getData();
            response.setBody(dataString);
        } catch (IOException e) {

        }
        return response;
    }

    public String getData() throws IOException {
        byte[] data = Files.readAllBytes(path);
        return new String(data);
    }
}

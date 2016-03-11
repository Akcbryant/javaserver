package javaserver;

import java.util.List;
import java.util.Arrays;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

public class RequestHandler {

    private static final List allowedMethods = Arrays.asList("GET","PUT","POST","OPTION");
    private static final List allowedPaths = Arrays.asList("/", "/form");
    private Request request;
    private Response response;

    RequestHandler(Request request) {
        this.request = request;
    }

    public Response handleRequest() {
        response = new ResponseBuilder().buildResponse();
        String method = request.getMethod();

        if (method.equals("GET")) {
            getRequest(request);
            return response;
        } else if (method.equals("POST")) {
            postRequest(request);
            return response;
        } else {
            return response;
        }
    }

    public void postRequest(Request request) {
        try {
            Path path = Paths.get("test");
            byte[] bodyData = request.getBody().getBytes();
            Files.write(path, bodyData);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void getRequest(Request request) {
        Path path = Paths.get("test");
        try {
            byte[] data = Files.readAllBytes(path);
            String dataString = new String(data);
            response.setBody(dataString);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}

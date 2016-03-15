package javaserver;

import java.util.List;
import java.util.Arrays;

public class RequestHandler {

    private Request request;
    private Response response;

    RequestHandler(Request request) {
        this.request = request;
    }

    public Response handleRequest() {
        response = new ResponseBuilder().buildResponse();
        String method = request.getMethod();

        if (method.equals("GET")) {
            response = new Get().handleRequest(request);
        } else if (method.equals("POST") || method.equals("PUT")) {
            response = new PostPut().handleRequest(request);
        } else if (method.equals("DELETE")) {
            response = new Delete().handleRequest(request);
        }

        return response;
    }
}

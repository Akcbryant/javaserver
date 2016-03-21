package javaserver;

public class ErrorHandler extends Handler {

    public Response handleRequest(Request request) {
        return new ResponseBuilder().buildFailedResponse();
    }
}

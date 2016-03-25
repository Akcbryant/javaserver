package javaserver;

public class ErrorHandler extends Handler {

    Response response;
    public Response handleRequest(Request request) {
        return response;
    }

    ErrorHandler(Status status) {
        response = new ResponseBuilder().buildResponse(Status.NotFound);
        response.setStatus(status);
    }
}

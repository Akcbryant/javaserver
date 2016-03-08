package javaserver;

public class ResponseBuilder {

    private Request request;
    private Response response;

    ResponseBuilder(Request request) {
        this.request = request;
    }

    public Response buildResponse() {
        response = new Response();

        response.setStatusCode("200");
        response.setStatusMessage("OK");

        return response;
    }
}

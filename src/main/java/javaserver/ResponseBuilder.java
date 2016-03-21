package javaserver;

public class ResponseBuilder {

    private Response response;

    public Response buildResponse() {
        response = new Response();

        response.setStatusCode("200");
        response.setStatusMessage("OK");

        return response;
    }

    public Response buildFailedResponse() {
        response = new Response();

        response.setStatusCode("404");
        response.setStatusMessage("Not Found");

        return response;
    }
}

package javaserver;

public class ResponseBuilder {

    private Response response;

    public Response buildResponse() {
        response = new Response();

        response.setStatusCode("200");
        response.setStatusMessage("OK");

        return response;
    }
}

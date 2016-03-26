package javaserver;

public class ResponseBuilder {

    private Response response;

    public Response buildResponse(Status status) {
        response = new Response();

        response.setStatus(status);

        return response;
    }
}

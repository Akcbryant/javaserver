package javaserver;

public class ResponseBuilder {

    private Response response;

    public Response buildResponse() {
        response = new Response();

        response.setStatus(Status.OK);

        return response;
    }

    public Response buildFailedResponse() {
        response = new Response();

        response.setStatus(Status.NOTFOUND);

        return response;
    }
}

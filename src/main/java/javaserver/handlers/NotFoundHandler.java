package javaserver.handlers;

import javaserver.Request;

public class NotFoundHandler implements Handler {

    public Response handleRequest(Request request) {
        Response response = new Response();

        response.setStatus(Status.NotFound);

        return response;
    }
}

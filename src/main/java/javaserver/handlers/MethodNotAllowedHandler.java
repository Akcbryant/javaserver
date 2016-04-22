package javaserver.handlers;

import javaserver.Request;

public class MethodNotAllowedHandler implements Handler {

    public Response handleRequest(Request request) {
        Response response = new Response();

        response.setStatus(Status.METHOD_NOT_ALLOWED);

        return response;
    }
}

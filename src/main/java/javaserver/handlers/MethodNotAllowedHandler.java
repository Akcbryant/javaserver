package javaserver.handlers;

import javaserver.Request;

public class MethodNotAllowedHandler implements Handler {

    public Response handleRequest(Request request) {
        Response response = new Response();

        response.setStatus(Status.MethodNotAllowed);

        return response;
    }
}

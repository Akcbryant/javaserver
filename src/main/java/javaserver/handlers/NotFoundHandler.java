package javaserver.handlers;

import javaserver.Request;

public class NotFoundHandler implements Handler {

    private static final String NOT_FOUND_MESSAGE = "Nothing Found Here";

    public Response handleRequest(Request request) {
        Response response = new Response();

        response.setBody(NOT_FOUND_MESSAGE);
        response.setStatus(Status.NOT_FOUND);

        return response;
    }
}

package javaserver.handlers;

import javaserver.Request;

public class HeadHandler implements Handler {

    public Response handleRequest(Request request) {
        Response response = new Response();

        response.setStatus(Status.Ok);

        return response;
    }
}

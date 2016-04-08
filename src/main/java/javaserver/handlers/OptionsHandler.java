package javaserver.handlers;

import javaserver.Request;

public class OptionsHandler implements Handler {

    private static final String ALLOWHEADER = "Allow:";
    String availableMethods;

    public OptionsHandler(String availableMethods) {
        this.availableMethods = availableMethods;
    }

    public Response handleRequest(Request request) {
        Response response = new Response();

        response.setStatus(Status.Ok);

        String headers = ALLOWHEADER + availableMethods;
        response.setHeaders(headers);

        return response;
    }
}

package javaserver.handlers;

import javaserver.Request;

public class RedirectHandler implements Handler {

    private String redirect;

    public RedirectHandler(String redirect) {
        this.redirect = redirect;
    }

    public Response handleRequest(Request request) {
        Response response = new Response();

        response.setStatus(Status.REDIRECT);
        response.setHeaders(getRedirectHeader(redirect));

        return response;
    }

    private String getRedirectHeader(String location) {
        return "Location: " + location;
    }
}

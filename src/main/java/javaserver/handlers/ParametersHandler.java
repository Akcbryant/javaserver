package javaserver.handlers;

import javaserver.Request;
import javaserver.helpers.ParameterDecoder;

public class ParametersHandler implements Handler {

    ParameterDecoder decoder = new ParameterDecoder();

    public Response handleRequest(Request request) {
        Response response = new Response();

        response.setStatus(Status.OK);

        String body = request.getParameters();
        response.setBody(body);

        return response;
    }
}

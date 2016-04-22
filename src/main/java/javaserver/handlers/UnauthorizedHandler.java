package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

import java.io.IOException;

public class UnauthorizedHandler implements Handler {

    private static final String LOGS_LOCATION = "/logs";
    protected static final String AUTH_HEADER = "WWW-Authenticate: Basic=javaserver";

    private ResourceUtility resourceUtility;
    private String fileUri;
    private Response response = new Response();

    public UnauthorizedHandler(String fileUri, ResourceUtility resourceUtility) {
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    public Response handleRequest(Request request) {
        response.setStatus(Status.UNAUTHORIZED);

        response.setHeaders(AUTH_HEADER);

        String firstLine = request.getFirstLine();
        logString(fileUri, firstLine, resourceUtility);

        return response;
    }

    protected void logString(String fileUri, String text, ResourceUtility resourceUtility) {
        try {
            String logLocation = fileUri + LOGS_LOCATION;
            resourceUtility.updateResource(logLocation, text.getBytes());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}

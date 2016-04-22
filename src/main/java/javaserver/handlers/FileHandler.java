package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

import java.io.IOException;

public class FileHandler implements Handler {

    protected ResourceUtility resourceUtility;
    protected String fileUri;
    protected Response response = new Response();

    public FileHandler() {

    }

    public FileHandler(String fileUri, ResourceUtility resourceUtility) {
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    public Response handleRequest(Request request) {
        String method = request.getMethod();

        if (!method.equals("GET")) {
            response.setStatus(Status.METHOD_NOT_ALLOWED);
            return response;
        }

        byte[] body = getFileContents(fileUri, resourceUtility);
        response.setBody(body);
        return response;
    }

    public byte[] getFileContents(String fileUri, ResourceUtility resourceUtility) {
        try {
            byte[] content = resourceUtility.readResource(fileUri);
            response.setStatus(Status.OK);
            return content;
        } catch (IOException e) {
            response.setStatus(Status.SERVER_ERROR);
            return new byte[0];
        }
    }
}

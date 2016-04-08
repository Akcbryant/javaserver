package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

import java.io.IOException;

public class PostPutHandler implements Handler {

    private String fileUri;
    private ResourceUtility resourceUtility;

    public PostPutHandler(String fileUri, ResourceUtility resourceUtility) {
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    public Response handleRequest(Request request) {
        Response response = new Response();

        try {
            writeData(fileUri, request.getBody().getBytes(), resourceUtility);
            response.setStatus(Status.Ok);
        } catch (IOException e) {
            response.setStatus(Status.ServerError);
        }
        return response;
    }

    public void writeData(String fileUri, byte[] data, ResourceUtility resourceUtility) throws IOException {
        resourceUtility.createResource(fileUri, data);
    }
}

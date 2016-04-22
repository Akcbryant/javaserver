package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

import java.io.IOException;

public class DeleteHandler implements Handler {

    String fileUri;
    ResourceUtility resourceUtility;

    public DeleteHandler(String fileUri, ResourceUtility resourceUtility) {
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    public Response handleRequest(Request request) {
        Response response = new Response();
        try {
            deleteFile(fileUri, resourceUtility);
            response.setStatus(Status.OK);
        } catch (IOException e) {
            response.setStatus(Status.SERVER_ERROR);
        }
        return response;
    }

    public void deleteFile(String fileUri, ResourceUtility resourceUtility) throws IOException {
        resourceUtility.deleteResource(fileUri);
    }
}

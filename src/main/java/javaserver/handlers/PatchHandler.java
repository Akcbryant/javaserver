package javaserver.handlers;

import javaserver.Request;
import javaserver.helpers.SHA1Encoder;
import javaserver.utility.ResourceUtility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class PatchHandler implements Handler {

    private String fileUri;
    private Response response = new Response();
    private ResourceUtility resourceUtility;

    public PatchHandler(String fileUri, ResourceUtility resourceUtility) {
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    public Response handleRequest(Request request) {
        String etag = request.getHeaders().get("If-Match");

        if (matchesEtag(etag)) {
            byte[] data = request.getBody().getBytes();
            writeData(fileUri, data, resourceUtility);
        }

        response.setStatus(Status.NoContent);
        return response;
    }

    protected boolean matchesEtag(String etag) {
        byte[] fileContents = getFileContents(fileUri, resourceUtility);
        String fileSHA = SHA1Encoder.generateSHA(fileContents);
        return fileSHA.equals(etag);
    }

    private byte[] getFileContents(String fileUri, ResourceUtility resourceUtility) {
            try {
                return resourceUtility.readResource(fileUri);
            } catch (IOException e) {
                return new byte[0];
            }
    }

    private void writeData(String fileUri, byte[] data, ResourceUtility resourceUtility) {
        try {
            resourceUtility.createResource(fileUri, data);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}

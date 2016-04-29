package javaserver.handlers;

import javaserver.Request;
import javaserver.helpers.SHA1Encoder;
import javaserver.utility.ResourceUtility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class PatchHandler extends FileHandler {

    private static final String ETAG_HEADER = "If-Match";

    public PatchHandler(String fileUri, ResourceUtility resourceUtility) {
        super(fileUri, resourceUtility);
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    @Override
    public Response handleRequest(Request request) {
        String etag = request.getHeaders().get(ETAG_HEADER);

        if (matchesEtag(etag)) {
            byte[] data = request.getBody().getBytes();
            writeData(fileUri, data, resourceUtility);
        }

        response.setStatus(Status.NO_CONTENT);
        return response;
    }

    protected boolean matchesEtag(String etag) {
        byte[] fileContents = getFileContents(fileUri, resourceUtility);
        String fileSHA = SHA1Encoder.generateSHA(fileContents);
        return fileSHA.equals(etag);
    }

    @Override
    public byte[] getFileContents(String fileUri, ResourceUtility resourceUtility) {
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

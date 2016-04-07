package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

public class ImageHandler extends FileHandler {

    private static final String CONTENT_LENGTH = "Content-Length: ";
    private static final String IMAGE_TYPE = "Content-Type: image/";
    private static final String NEWLINE = System.getProperty("line.separator");

    private String fileUri;
    private Response response = new Response();
    private ResourceUtility resourceUtility;

    public ImageHandler(String fileUri, ResourceUtility resourceUtility) {
        super(fileUri, resourceUtility);
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    public Response handleRequest(Request request) {

        byte[] body = getFileContents(fileUri, resourceUtility);
        response.setBody(body);

        String imageType = getImageType(fileUri);
        String headers = makeImageHeaders(body.length, imageType);

        response.setHeaders(headers);
        return response;
    }

    protected String getImageType(String fileUri) {
        return fileUri.split("\\.")[1];
    }

    protected String makeImageHeaders(int contentLength, String imageType) {
        return CONTENT_LENGTH + contentLength + NEWLINE +
               IMAGE_TYPE + imageType + NEWLINE;
    }
}

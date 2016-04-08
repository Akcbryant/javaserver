package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;

public class FileHandler implements Handler {

    private final static List ACCEPTABLE_IMAGE_FORMATS = Arrays.asList(".jpg", ".gif", ".png");

    private int contentSize = 0;
    protected ResourceUtility resourceUtility;
    protected String fileUri;
    protected Response response = new Response();

    public FileHandler(String fileUri, ResourceUtility resourceUtility) {
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    public Response handleRequest(Request request) {

        String method = request.getMethod();
        if (!method.equals("GET")) {
            response.setStatus(Status.MethodNotAllowed);
            return response;
        }

        if (isPartialRequest(request)) {
            response = new PartialHandler(fileUri, resourceUtility).handleRequest(request);
            return response;
        }

        if (isImageRequest(request)) {
            response = new ImageHandler(fileUri, resourceUtility).handleRequest(request);
            return response;
        }

        byte[] body = getFileContents(fileUri, resourceUtility);
        contentSize = body.length;
        response.setBody(body);

        return response;
    }

    public byte[] getFileContents(String fileUri, ResourceUtility resourceUtility) {
        try {
            byte[] content = resourceUtility.readResource(fileUri);
            response.setStatus(Status.Ok);
            return content;
        } catch (IOException e) {
            response.setStatus(Status.ServerError);
            return new byte[0];
        }
    }

    protected boolean isPartialRequest(Request request) {
        return request.getHeaders().containsKey("Range");
    }

    protected boolean isImageRequest(Request request) {
        String uri = request.getUri();
        String[] splitUri = uri.split("\\.");

        return (splitUri.length > 1 && 
                ACCEPTABLE_IMAGE_FORMATS.contains(splitUri[1]));
    }
}

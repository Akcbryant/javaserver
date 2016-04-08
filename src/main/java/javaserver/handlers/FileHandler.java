package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;

public class FileHandler implements Handler {

    private final static List ACCEPTABLE_IMAGE_FORMATS = Arrays.asList(".jpg", ".gif", ".png");

    protected final static String GET = "GET";
    protected final static String RANGE_HEADER = "Range";
    protected ResourceUtility resourceUtility;
    protected String fileUri;
    protected Response response = new Response();

    public FileHandler(String fileUri, ResourceUtility resourceUtility) {
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    public Response handleRequest(Request request) {

        if (!isGetRequest(request)) {
            response.setStatus(Status.MethodNotAllowed);
            return response;
        }

        Handler fileHandler = getTypeOfFileHandler(request);
        if (fileHandler != null) {
            return fileHandler.handleRequest(request);
        }

        byte[] body = getFileContents(fileUri, resourceUtility);
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

    private Handler getTypeOfFileHandler(Request request) {
        if (isPartialRequest(request)) { return new PartialHandler(fileUri, resourceUtility); }
        if (isImageRequest(request)) { return new ImageHandler(fileUri, resourceUtility); }
        else { return null };
    }

    protected boolean isPartialRequest(Request request) {
        return request.getHeaders().containsKey(RANGE_HEADER);
    }

    protected boolean isImageRequest(Request request) {
        String uri = request.getUri();
        String[] splitUri = uri.split("\\.");

        return (splitUri.length > 1 && 
                ACCEPTABLE_IMAGE_FORMATS.contains(splitUri[1]));
    }

    private boolean isGetRequest(Request request) {
        return request.getMethod().equals(GET);
    }
}

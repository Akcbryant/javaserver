package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

import java.util.Arrays;

public class PartialHandler extends FileHandler {

    private static final String BYTE_SEPARATOR = "-";
    private static final String HEADER_SEPARATOR = "=";
    private static final String CONTENT_LENGTH_HEADER = "Content-Length: ";
    private static final String RANGE_HEADER = "Range";
    private static final String NEWLINE = System.getProperty("line.separator");

    private String fileUri;
    private ResourceUtility resourceUtility;
    private Response response = new Response();

    public PartialHandler() {

    }

    public PartialHandler(String fileUri, ResourceUtility resourceUtility) {
        super(fileUri, resourceUtility);
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    public Response handleRequest(Request request) {
        String range = request.getHeaders().get(RANGE_HEADER);

        byte[] body = getFileContents(fileUri, resourceUtility);

        int start = getFirstNumberInRange(range, body.length);
        int end = getLastNumberInRange(range, body.length);

        body = Arrays.copyOfRange(body, start, end);

        response.setBody(body);
        response.setHeaders(makeHeaders(body.length));
        response.setStatus(Status.PartialContent);

        return response;
    }

    public int getFirstNumberInRange(String contentRange, int contentLength) {
        String range = contentRange.split(HEADER_SEPARATOR)[1];
        String firstNumber = range.split(BYTE_SEPARATOR)[0];

        if (range.startsWith(BYTE_SEPARATOR)) {
            int number = Integer.parseInt(range.substring(1));
            return  Math.max(contentLength - number, 0);
        } else {
            return Integer.parseInt(firstNumber);
        }
    }

    public int getLastNumberInRange(String contentRange, int contentLength) {
        String range = contentRange.split(HEADER_SEPARATOR)[1];

        if (range.endsWith(BYTE_SEPARATOR) || range.startsWith(BYTE_SEPARATOR)) {
            return contentLength;
        } else {
            int lastNumber = Integer.parseInt(range.split(BYTE_SEPARATOR)[1]);
            return lastNumber + 1;
        }
    }

    private String makeHeaders(int contentLength) {
        String headers = CONTENT_LENGTH_HEADER + contentLength + NEWLINE; 
        return headers;
    }

}


package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

import java.util.Arrays;
import java.util.List;

public class FileHandlerDecider {

    private final static String TYPE_SPLIT = "\\.";

    private final static List ACCEPTABLE_IMAGE_FORMATS = Arrays.asList("jpg", "gif", "png");

    public static FileHandler decideHandler(Request request, String fileUri, ResourceUtility resourceUtility) {
        if (isPartialRequest(request)) { return new PartialHandler(fileUri, resourceUtility); }
        if (isImageRequest(request)) { return new ImageHandler(fileUri, resourceUtility); }
        if (isPatchRequest(request)) { return new PatchHandler(fileUri, resourceUtility); }
        else { return new FileHandler(fileUri, resourceUtility); }
    }

    private static boolean isPartialRequest(Request request) {
        return request.getHeaders().containsKey(PartialHandler.RANGE_HEADER);
    }

    private static boolean isPatchRequest(Request request) {
        return request.getMethod().equals("PATCH");
    }

    private static boolean isImageRequest(Request request) {
        String uri = request.getUri();
        String[] splitUri = uri.split(TYPE_SPLIT);

        if (splitUri.length > 1) {
        return ACCEPTABLE_IMAGE_FORMATS.contains(splitUri[1]);
        }
        return false;
    }
}

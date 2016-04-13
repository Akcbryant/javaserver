package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;

import java.util.Arrays;
import java.util.List;

public class FileHandlerDecider {

    private final static String TYPE_SPLIT = "\\.";
    private final static String RANGE_HEADER = "Range";

    private final static List ACCEPTABLE_IMAGE_FORMATS = Arrays.asList(".jpg", ".gif", ".png");

    public static FileHandler decideHandler(Request request, String fileUri, ResourceUtility resourceUtility) {
        if (isPartialRequest(request)) { return new PartialHandler(fileUri, resourceUtility); }
        if (isImageRequest(request)) { return new ImageHandler(fileUri, resourceUtility); }
        else return new FileHandler(fileUri, resourceUtility);
    }

    private static boolean isPartialRequest(Request request) {
        return request.getHeaders().containsKey(RANGE_HEADER);
    }

    private static boolean isImageRequest(Request request) {
        String uri = request.getUri();
        String[] splitUri = uri.split(TYPE_SPLIT);

        return (splitUri.length > 1 && ACCEPTABLE_IMAGE_FORMATS.contains(splitUri[1]));
    }
}

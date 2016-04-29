package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.FileUtility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.HashMap;

public class FileHandlerDeciderTest {

    private static final String TEST_STRING = "test";
    private static final String IMAGE_URI = "/test.png";

    private Request request = new Request();
    private FileHandler fileHandler;

    private FileHandler decideFileHandler(Request request) {
        return FileHandlerDecider.decideHandler(request, "", new FileUtility());
    }

    @Test
    public void returnPartialHandlerWhenGivenAPartialRequest() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(PartialHandler.RANGE_HEADER, TEST_STRING);
        request.setHeaders(headers);

        fileHandler = decideFileHandler(request);

        assertEquals(new PartialHandler(null, null).getClass(), fileHandler.getClass());
    }

    @Test
    public void returnImageHandlerWhenGivenAnImageUri() {
        request = new Request();
        request.setUri(IMAGE_URI);

        fileHandler = decideFileHandler(request);

        assertEquals(new ImageHandler(null, null).getClass(), fileHandler.getClass());
    }

    @Test
    public void returnPatchHandlerWhenTheHTTPMethodIsPatch() {
        request.setMethod("PATCH");

        fileHandler = decideFileHandler(request);

        assertEquals(new PatchHandler(null, null).getClass(), fileHandler.getClass());
    }

    @Test
    public void aLackOfRangeHeaderIndicatesItIsNotAPartialRequest() {
        request = new Request();

        fileHandler = decideFileHandler(request);

        assertEquals(new FileHandler(null, null).getClass(), fileHandler.getClass());
    }
}

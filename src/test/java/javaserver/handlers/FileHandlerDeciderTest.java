package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.FileUtility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.HashMap;

public class FileHandlerDeciderTest {

    Request request = new Request();

    @Test
    public void aRangeHeaderIndicatesAPartialRequest() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Range", "test");
        request.setHeaders(headers);

        FileHandler fileHandler = FileHandlerDecider.decideHandler(request, "", new FileUtility());
        assertEquals(new PartialHandler().getClass(), fileHandler.getClass());
    }

    @Test
    public void aLackOfRangeHeaderIndicatesItIsNotAPartialRequest() {
        request = new Request();

        FileHandler fileHandler = FileHandlerDecider.decideHandler(request, "", new FileUtility());
        assertEquals(new FileHandler().getClass(), fileHandler.getClass());
    }
}

package javaserver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RequestTest {

    private Request request;

    private static final String NEWLINE = Request.REQUEST_NEWLINE;
    private static final String SPACE = Request.SPACE;
    private static final String EMPTY_HEADERS = "{}";

    private static final String EMPTY_REQUEST = SPACE + SPACE + NEWLINE + EMPTY_HEADERS + NEWLINE + NEWLINE;

    @Test
    public void aRequestObjectIsAnEmptyStringByDefault() {
        request = new Request();

        assertEquals(EMPTY_REQUEST, request.toString());
    }
}

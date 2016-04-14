package javaserver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RequestTest {

    private Request request;

    private static final String NEW_LINE = Request.NEW_LINE;
    private static final String SPACE = Request.SPACE;
    private static final String EMPTY_HEADERS = "{}";

    private static final String EMPTY_REQUEST = SPACE + SPACE + NEW_LINE + EMPTY_HEADERS + NEW_LINE + NEW_LINE;

    @Test
    public void aRequestObjectIsAnEmptyStringByDefault() {
        request = new Request();

        assertEquals(EMPTY_REQUEST, request.toString());
    }
}

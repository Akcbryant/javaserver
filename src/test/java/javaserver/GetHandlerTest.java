package javaserver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.HashMap;
import java.io.IOException;

public class GetHandlerTest {

    HashMap headers = new HashMap<String, String>();
    Request request;
    Response response;

    @Test
    public void testBodyIsSetOnSuccessfulGetRequest() {
        request = new Request("GET", "/", "HTTP/1.1", headers, "");

        response = new GetHandlerMock().handleRequest(request);

        assertEquals("Test String", response.getBody());
    }

    @Test
    public void testPathFailure() {
        request = new Request("GET", "/foobar", "HTTP/1.1", headers, "");

        response = new GetHandler().handleRequest(request);

        assertEquals(Status.NOTFOUND, response.getStatus());
    }

    private class GetHandlerMock extends GetHandler {

        @Override
        public String getData() {
            return "Test String";
        }
    }

}

package javaserver.handlers;

import javaserver.Request;

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

        assertEquals(Status.Ok, response.getStatus());
        assertEquals("Test String", response.getBody());
    }

    @Test
    public void testForFailedReading() {
        request = new Request();

        response = new GetHandlerFailsMock().handleRequest(request);

        assertEquals(Status.Ok, response.getStatus());
        assertEquals("", response.getBody());
    }

    private class GetHandlerMock extends GetHandler {

        @Override
        public String getData() {
            return "Test String";
        }
    }

    private class GetHandlerFailsMock extends GetHandler {

        @Override
        public String getData() throws IOException {
            throw new IOException();
        }
    }

}

package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RedirectHandlerTest {

    @Test
    public void handleRequest_GivenRedirectRequest_ResponseContainsLocationHeader() {
        Request request = new Request();
        Response response = new RedirectHandler("http://localhost:5000/").handleRequest(request);

        assertEquals(Status.Redirect, response.getStatus());
        assertEquals("Location: http://localhost:5000/", response.getHeaders());
    }
}

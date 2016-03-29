package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MethodNotAllowedHandlerTest {

    @Test
    public void statusAlwaysGetsSetToMethodNotAllowed() {
        Request request = new Request();
        Response response = new MethodNotAllowedHandler().handleRequest(request);

        assertEquals(Status.MethodNotAllowed, response.getStatus());
    }
}

package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HeadHandlerTest {

    @Test
    public void headHandlerAlwaysReturnsOk() {
        HeadHandler headHandler = new HeadHandler();

        Request request = new Request();
        Response response = headHandler.handleRequest(request);

        assertEquals(Status.Ok, response.getStatus());
    }
}

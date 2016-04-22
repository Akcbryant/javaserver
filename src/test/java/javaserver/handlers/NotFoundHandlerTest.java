package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NotFoundHandlerTest {

    @Test
    public void notFoundHandlerSetsStatusToNotFound() {

        Request request = new Request();
        Response response = new NotFoundHandler().handleRequest(request);

        assertEquals(Status.NOT_FOUND, response.getStatus());

    }
}

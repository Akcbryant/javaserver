package javaserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import java.util.HashMap;

public class RequestHandlerTest {

    RequestHandler requestHandler = new RequestHandler();
    Response response;
    Request request = new Request();
    Handler handler;

    @Test
    public void handleRequestAlwaysReturnAResponse() {
        response = requestHandler.handleRequest(request);

        assertNotNull(response);
    }

    @Test
    public void determineHandlerReturnsGetHandlerWhenRequestIsGet() {
        handler = determineHandler("GET", "/");

        assertEquals(handler.getClass(), new GetHandler().getClass());
    }

    @Test
    public void determineHandlerReturnsPostPutHandlerWhenRequestIsPost() {
        handler = determineHandler("POST", "/form");

        assertEquals(handler.getClass(), new PostPutHandler().getClass());
    }

    @Test
    public void determineHandlerReturnsPostPutHandlerWhenRequestIsPut() {
        handler = determineHandler("PUT", "/form");

        assertEquals(handler.getClass(), new PostPutHandler().getClass());
    }

    @Test
    public void determineHandlerReturnsDeleteHandlerWhenRequestIsDelete() {
        handler = determineHandler("DELETE", "/form");

        assertEquals(handler.getClass(), new DeleteHandler().getClass());
    }

    @Test
    public void determineHandlerReturnsOptionsHandlerWhenRequestIsOptions() {
        handler = determineHandler("OPTIONS", "/form");

        assertEquals(handler.getClass(), new OptionsHandler().getClass());
    }

    private Handler determineHandler(String method, String uri) {
        request.setMethod(method);
        request.setUri(uri);
        return requestHandler.determineHandler(request);
    }

    @Test
    public void whenThereAreParametersSetThemAsResponseBody() {
        request = new Request();
        request.setUri("/parameters?variable1=%20%3C%2C%20&variable2=stuff");

        response = requestHandler.handleRequest(request);

        assertEquals("variable1 =  <, \r\nvariable2 = stuff", response.getBody());
    }

    @Test
    public void testPathFailure() {
        request = new Request();
        request.setMethod("GET");
        request.setUri("/foobar");

        response = requestHandler.handleRequest(request);

        assertEquals(Status.NotFound, response.getStatus());
    }

    @Test
    public void handleRequest_GivenRedirectRequest_ResponseContainsLocationHeader() {
        request = new Request();
        request.setMethod("GET");
        request.setUri("/redirect");

        response = requestHandler.handleRequest(request);

        assertEquals(Status.Redirect, response.getStatus());
        assertEquals("Location: http://localhost:5000/", response.getHeaders());
    }
}

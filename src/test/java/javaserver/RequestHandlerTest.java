package javaserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import java.util.HashMap;

public class RequestHandlerTest {

    HashMap<String, String> headers = new HashMap<String, String>();
    RequestHandler requestHandler = new RequestHandler();
    Response response;
    Request request;
    Handler handler;

    @Test
    public void handleRequestAlwaysReturnAResponse() {
        request = new Request("", "", "", headers, "");

        response = requestHandler.handleRequest(request);

        assertNotNull(response);
    }

    @Test
    public void determineHandlerReturnsGetHandlerWhenRequestIsGet() {
        handler = determineHandler("GET");

        assertEquals(handler.getClass(), new GetHandler().getClass());
    }

    @Test
    public void determineHandlerReturnsPostPutHandlerWhenRequestIsPost() {
        handler = determineHandler("POST");

        assertEquals(handler.getClass(), new PostPutHandler().getClass());
    }

    @Test
    public void determineHandlerReturnsPostPutHandlerWhenRequestIsPut() {
        handler = determineHandler("PUT");

        assertEquals(handler.getClass(), new PostPutHandler().getClass());
    }

    @Test
    public void determineHandlerReturnsDeleteHandlerWhenRequestIsDelete() {
        handler = determineHandler("DELETE");

        assertEquals(handler.getClass(), new DeleteHandler().getClass());
    }

    private Handler determineHandler(String method) {
        request = new Request(method, "/", "HTTP/1.1", headers, "");
        return requestHandler.determineHandler(request);
    }
}

package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class OptionsHandlerTest {

    OptionsHandler handler = new OptionsHandler("GET, POST, OPTIONS");
    Request request = new Request();
    Response response;

    @Test
    public void optionsHandlerGetsOptionsForRoute() {
        response = handler.handleRequest(request);

        assertTrue(response.getHeaders().contains("GET"));
        assertTrue(response.getHeaders().contains("POST"));
        assertTrue(response.getHeaders().contains("OPTIONS"));
        assertTrue(response.getHeaders().contains("Allow:"));
    }
}

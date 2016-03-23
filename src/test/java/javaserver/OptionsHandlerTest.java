package javaserver;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class OptionsHandlerTest {

    Router router = new Router();
    OptionsHandler handler = new OptionsHandler(router);
    Request request = new Request();
    Response response;

    @Test
    public void optionsHandlerGetsOptionsForRoute() {
        request.setUri("/form");
        response = handler.handleRequest(request);

        assertTrue(response.getHeaders().contains("GET"));
        assertTrue(response.getHeaders().contains("POST"));
        assertTrue(response.getHeaders().contains("OPTIONS"));
        assertTrue(response.getHeaders().contains("PUT"));
        assertTrue(response.getHeaders().contains("DELETE"));
        assertTrue(response.getHeaders().contains("Allow:"));
    }
}

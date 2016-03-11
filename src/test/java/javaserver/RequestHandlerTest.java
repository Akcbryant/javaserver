package javaserver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.HashMap;

public class RequestHandlerTest {

    @Test
    public void requestSucceeds() {
        HashMap<String, String> headers = new HashMap<String, String>();
        Request request = new Request("PUT", "/form", "HTTP/1.1\r\n", headers, "data=test");
        Response response = new RequestHandler(request).handleRequest();

        assertEquals("200", response.getStatusCode());
        assertEquals("OK", response.getStatusMessage());
    }

}

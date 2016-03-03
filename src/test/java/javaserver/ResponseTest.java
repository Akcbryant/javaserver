package javaserver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ResponseTest {

    public void createResponse() {
        Request request = new Request("GET", "/", "HTTP/1.1");
        Response response = new Response(request);
        assertEquals(response.getResponse(), "GET");
    }
}

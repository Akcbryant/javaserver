package javaserver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.HashMap;

public class GetTest {

    @Test
    public void testPathFailure() {
        HashMap headers = new HashMap<String, String>();
        Request request = new Request("GET", "/foobar", "HTTP/1.1", headers, "");

        Response response = new Get().handleRequest(request);

        assertEquals("404", response.getStatusCode());
    }
}

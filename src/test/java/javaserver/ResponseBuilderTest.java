package javaserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.HashMap;

public class ResponseBuilderTest {

    private Request request;
    private Response response;
    private HashMap<String, String> headers = new HashMap<String, String>();

    @Test
    public void getRequestRespondsOK() {
        Request request = new Request("GET", "/", "HTTP/1.1", headers, "");

        Response response = new ResponseBuilder(request).buildResponse();

        assertEquals(response.getStatusCode(), "200");
    }

}

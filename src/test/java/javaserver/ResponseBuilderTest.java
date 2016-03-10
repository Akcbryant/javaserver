package javaserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.HashMap;

public class ResponseBuilderTest {

    private Response response;
    private HashMap<String, String> headers = new HashMap<String, String>();

    @Test
    public void getRequestRespondsOK() {
        Response response = new ResponseBuilder().buildResponse();

        assertEquals(response.getStatusCode(), "200");
    }
}

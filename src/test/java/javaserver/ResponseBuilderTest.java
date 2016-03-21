package javaserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.HashMap;

public class ResponseBuilderTest {

    private Response response;
    private HashMap<String, String> headers = new HashMap<String, String>();

    @Test
    public void buildSuccessfulResponse() {
        Response response = new ResponseBuilder().buildResponse();

        assertEquals(response.getStatus(), Status.OK);
    }

    @Test
    public void buildFailedResponse() {
        response = new ResponseBuilder().buildFailedResponse();

        assertEquals(response.getStatus(), Status.NOTFOUND);
    }
}

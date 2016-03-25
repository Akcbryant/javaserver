package javaserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.HashMap;

public class ResponseBuilderTest {

    private Response response;
    private HashMap<String, String> headers = new HashMap<String, String>();

    @Test
    public void buildSuccessfulResponse() {
        Response response = new ResponseBuilder().buildResponse(Status.Ok);

        assertEquals(Status.Ok, response.getStatus());
    }

    @Test
    public void buildNotFoundResponse() {
        response = new ResponseBuilder().buildResponse(Status.NotFound);

        assertEquals(Status.NotFound, response.getStatus());
    }

    @Test
    public void buildRedirectResponse() {
        response = new ResponseBuilder().buildResponse(Status.Redirect);

        assertEquals(Status.Redirect, response.getStatus());
    }
}

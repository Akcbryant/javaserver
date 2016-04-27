package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParametersHandlerTest {

    private static final String PARAMS = "variable1";
    @Test
    public void givenUriWithParametersTheBodyGetsSetAsDecodedParameters() {
        Request request = new Request();
        request.setParameters(PARAMS);

        Response response = new ParametersHandler().handleRequest(request);

        assertEquals(Status.OK, response.getStatus());
        assertEquals(PARAMS, response.getBody());
    }

}

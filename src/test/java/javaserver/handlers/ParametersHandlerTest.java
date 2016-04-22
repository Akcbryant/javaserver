package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParametersHandlerTest {

    @Test
    public void handleRequest_GivenUriWithParameters_SetsBodyAsDecodedParameters() {
        Request request = new Request();
        request.setUri("/parameters?variable1=%20%3C%2C%20&variable2=stuff");

        Response response = new ParametersHandler().handleRequest(request);

        assertEquals(Status.OK, response.getStatus());
        assertEquals("variable1 =  <, \r\nvariable2 = stuff", response.getBody());
    }

}

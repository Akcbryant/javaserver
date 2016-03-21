package javaserver;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ErrorHandlerTest {

    @Test
    public void errorHandlerAlwaysReturnsNotFoundResponse() {
        HashMap<String, String> headers = new HashMap<String, String>();
        Request request = new Request();

        Response response = new ErrorHandler().handleRequest(request);

        assertEquals(Status.NOTFOUND, response.getStatus());
    }
}

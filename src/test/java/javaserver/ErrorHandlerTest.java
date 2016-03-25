package javaserver;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ErrorHandlerTest {

    ErrorHandler errorHandler;
    Request request = new Request();
    Response response;

    @Test
    public void errorHandlerReturnsNotFoundResponse() {
        errorHandler = new ErrorHandler(Status.NotFound);
        response = errorHandler.handleRequest(request);

        assertEquals(Status.NotFound, response.getStatus());
    }

    @Test
    public void errorHandlerReturnsMethodNotAllowedResponse() {
        errorHandler = new ErrorHandler(Status.MethodNotAllowed);

        response = errorHandler.handleRequest(request);

        assertEquals(Status.MethodNotAllowed, response.getStatus());
    }
}

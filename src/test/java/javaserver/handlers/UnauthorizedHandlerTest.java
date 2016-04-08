package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.FileUtility;
import javaserver.utility.ResourceUtility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UnauthorizedHandlerTest {

    @Test
    public void responseShouldHaveAUTH_HEADER() {
        Request request = new Request();

        Response response = new MockUnauthorizedHandler().handleRequest(request);

        assertEquals(UnauthorizedHandler.AUTH_HEADER, response.getHeaders());
    }

    private class MockUnauthorizedHandler extends UnauthorizedHandler {

        MockUnauthorizedHandler() {
            super("", new FileUtility());
        }

        @Override
        protected void logString(String fileUri, String text, ResourceUtility resourceUtility) {

        }
    }
}

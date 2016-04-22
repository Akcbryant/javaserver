package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.FileUtility;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FormHandlerTest {

    MockUtility utility;
    Response response;
    Request request = new Request();

    @Test
    public void responseStatusIsOkWhenFileContentsAreFound() {
        request.setMethod("GET");
        utility = new MockUtility(true);

        response = new FormHandler("", utility).handleRequest(request);

        assertEquals(Status.OK, response.getStatus());
    }

    @Test
    public void responseStatusIsOkWhenReadResourceFails() {
        request.setMethod("GET");
        utility = new MockUtility(false);

        response = new FormHandler("", utility).handleRequest(request);

        assertEquals(Status.OK, response.getStatus());
    }

    private class MockUtility extends FileUtility {

        private boolean isReadResourceSuccessful;

        MockUtility(boolean isReadResourceSuccessful) {
            this.isReadResourceSuccessful = isReadResourceSuccessful;
        }

        @Override
        public byte[] readResource(String location) throws IOException {
            if (isReadResourceSuccessful) {
                return "".getBytes();
            }
            throw new IOException();
        }
    }
}

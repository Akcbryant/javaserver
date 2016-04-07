package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.FileUtility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.IOException;

public class PatchHandlerTest {

    Request request = new Request();
    Response response;

    @Test
    public void responseStatusCodeEqualsNoContentWhenEtagDoesntMatch() {
        response = new MockPatchHandler(false).handleRequest(request);

        assertEquals(Status.NoContent, response.getStatus());
    }

    @Test
    public void responseToPatchShouldNeverHaveBody() {
        response = new MockPatchHandler(true).handleRequest(request);

        assertEquals("", response.getBody());
    }

    private class MockPatchHandler extends PatchHandler {

        boolean matches;

        MockPatchHandler(boolean matches) {
            super("", new FileUtility());
            this.matches = matches;
        }

        @Override
        protected boolean matchesEtag(String etag) {
            return matches;
        }
    }
}

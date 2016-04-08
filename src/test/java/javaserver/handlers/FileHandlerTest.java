package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.FileUtility;
import javaserver.utility.ResourceUtility;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FileHandlerTest {

    private static final String SUCCESSMESSAGE = "File Reading Successful";

    private FileHandler fileHandler = new FileHandler("", new FileUtility());
    private Request request = new Request();

    private Response response;
    private MockUtility mockUtility;

    @Test
    public void methodThatIsAnthingButGETSetsStatusToMethodNotAllowed() {
        request.setMethod("POST");

        response = fileHandler.handleRequest(request);

        assertEquals(Status.MethodNotAllowed, response.getStatus());
    }

    @Test
    public void aRangeHeaderIndicatesAPartialRequest() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Range", "test");
        request.setHeaders(headers);

        assertTrue(fileHandler.isPartialRequest(request));
    }

    @Test
    public void aLackOfRangeHeaderIndicatesItIsNotAPartialRequest() {
        request = new Request();

        assertFalse(fileHandler.isPartialRequest(request));
    }

    @Test
    public void setResponseBodyToSuccessfullyReadFileContents() {
        request.setMethod("GET");
        response = new MockFileHandler(true).handleRequest(request);

        assertEquals(SUCCESSMESSAGE, new String(response.getByteBody()));
    }

    @Test
    public void setResponseBodyToEmptyUponFailureToReadFileContents() {
        response = new MockFileHandler(false).handleRequest(request);

        assertEquals("", response.getBody());
    }

    @Test
    public void ifReadResourcesSucceedsResponseStatusIsSetToOk() {
        mockUtility = new MockUtility(true);
        request.setMethod("GET");

        response = new FileHandler("", mockUtility).handleRequest(request);

        assertEquals(Status.Ok, response.getStatus());
    }

    @Test
    public void ifReadResourcesFailsResponseStatusIsSetToServerErro() {
        mockUtility = new MockUtility(false);
        request.setMethod("GET");

        response = new FileHandler("", mockUtility).handleRequest(request);

        assertEquals(Status.ServerError, response.getStatus());
    }

    private class MockFileHandler extends FileHandler {

        private boolean fileReadSuccessful;

        MockFileHandler(boolean fileReadSuccessful) {
            super("", new FileUtility());
            this.fileReadSuccessful = fileReadSuccessful;
        }

        @Override
        public byte[] getFileContents(String fileUri, ResourceUtility resourceUtility) {
            if (fileReadSuccessful) {
                return SUCCESSMESSAGE.getBytes();
            }
            return "".getBytes();
        }
    }

    private class MockUtility extends FileUtility {

        private boolean isReadResourceSuccessful;

        MockUtility(boolean isReadResourceSuccessful) {
            this.isReadResourceSuccessful = isReadResourceSuccessful;
        }

        public byte[] readResource(String location) throws IOException {
            if (isReadResourceSuccessful) {
                return "".getBytes();
            }
            throw new IOException();
        }
    }
}

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

    private static final String SUCCESS_MESSAGE = "File Reading Successful";
    private static final String POST = "POST";
    private static final String TEST_STRING = "test";

    private static final boolean FILE_READ_SUCCEEDED = true;
    private static final boolean FILE_READ_FAILED = false;

    private FileHandler fileHandler = new FileHandler("", new FileUtility());
    private Request request = new Request();

    private Response response;
    private MockUtility mockUtility;

    @Test
    public void methodThatIsAnthingButGETSetsStatusToMethodNotAllowed() {
        request.setMethod(POST);

        response = fileHandler.handleRequest(request);

        assertEquals(Status.MethodNotAllowed, response.getStatus());
    }

    @Test
    public void aRangeHeaderIndicatesAPartialRequest() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(FileHandler.RANGE_HEADER, TEST_STRING);
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
        request.setMethod(FileHandler.GET);
        response = new MockFileHandler(FILE_READ_SUCCEEDED).handleRequest(request);

        assertEquals(SUCCESS_MESSAGE, new String(response.getByteBody()));
    }

    @Test
    public void setResponseBodyToEmptyUponFailureToReadFileContents() {
        response = new MockFileHandler(FILE_READ_FAILED).handleRequest(request);

        assertEquals("", response.getBody());
    }

    @Test
    public void ifReadResourcesSucceedsResponseStatusIsSetToOk() {
        mockUtility = new MockUtility(FILE_READ_SUCCEEDED);
        request.setMethod(FileHandler.GET);

        response = new FileHandler("", mockUtility).handleRequest(request);

        assertEquals(Status.Ok, response.getStatus());
    }

    @Test
    public void ifReadResourcesFailsResponseStatusIsSetToServerErro() {
        mockUtility = new MockUtility(FILE_READ_FAILED);
        request.setMethod(FileHandler.GET);

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
                return SUCCESS_MESSAGE.getBytes();
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

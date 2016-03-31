package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;

public class FileHandlerTest {

    private static final String SUCCESSMESSAGE = "File Reading Successful";

    Request request = new Request();
    Response response;

    public void fileHandler_GivenAnythingButAGetRequest_ReturnsMethodNotAllowed() {
        request.setMethod("POST");

        response = new FileHandler("").handleRequest(request);

        assertEquals(Status.MethodNotAllowed, response.getStatus());
    }

    public void fileHandler_SuccessfulFileRead_HasContentsInBody() {
        request.setMethod("GET");
        response = new MockFileHandlerContentsSuccessful().handleRequest(request);

        assertEquals(SUCCESSMESSAGE, response.getBody());
    }

    public void fileHandler_UnsuccessfulFileRead_HasEmptyBody() {
        response = new MockFileHandlerContentsUnsuccessful().handleRequest(request);

        assertEquals("", response.getBody());
    }

    private class MockFileHandlerContentsSuccessful extends FileHandler {

        MockFileHandlerContentsSuccessful() {
            super("");
        }

        @Override
        public String getFileContents(String fileUri) {
            return SUCCESSMESSAGE;
        }
    }

    private class MockFileHandlerContentsUnsuccessful extends FileHandler {

        MockFileHandlerContentsUnsuccessful() {
            super("");
        }

        @Override
        public String getFileContents(String fileUri) {
            return "";
        }
    }
}

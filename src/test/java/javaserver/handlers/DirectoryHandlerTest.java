package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DirectoryHandlerTest {

    Request request = new Request();
    Response response;

    @Test
    public void givenNoFilesReturnsAnEmptyBody() {
        response = new MockDirectoryHandler(true).handleRequest(request);

        assertEquals("", response.getBody());
    }

    @Test
    public void 

    @Test
    public void returnHTMLListingWhenFileArePresent() {
        response = new MockDirectoryHandler(false).handleRequest(request);

        String expected = String.format(DirectoryHandler.HTMLFORMAT, "/test", "test");
        assertEquals(expected + expected + expected, response.getBody());
    }

    private class MockDirectoryHandler extends DirectoryHandler {

        private boolean directoryIsEmpty;

        MockDirectoryHandler(boolean directoryIsEmpty) {
            super("");
            this.directoryIsEmpty = directoryIsEmpty;
        }

        @Override
        public String[] getFilesList(String directoryPath) {
            if (directoryIsEmpty) {
                return null;
            }
            return new String[] {"test", "test", "test"};
        }
    }
}

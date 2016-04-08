package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DirectoryHandlerTest {

    private static final String TEST_FILE_NAME = "test";
    private static final String TEST_URI = "/test";
    private static final String EXPECTED = String.format(DirectoryHandler.HTMLFORMAT, TEST_URI, TEST_FILE_NAME);

    private Request request = new Request();
    private Response response;

    @Test
    public void returnsAnEmptyBodyWhenGivenNoFiles() {
        response = new MockDirectoryHandler(true).handleRequest(request);

        assertEquals("", response.getBody());
    }

    @Test
    public void createHTMLListForFilesWhenGivenDirectoryIsNotEmpty() {
        response = new MockDirectoryHandler(false).handleRequest(request);

        assertEquals(EXPECTED + EXPECTED + EXPECTED, response.getBody());
    }

    private class MockDirectoryHandler extends DirectoryHandler {

        private boolean directoryIsEmpty;

        MockDirectoryHandler(boolean directoryIsEmpty) {
            super("");
            this.directoryIsEmpty = directoryIsEmpty;
        }

        @Override
        public String[] getNonHiddenFileNamesList(String directoryPath) {
            if (directoryIsEmpty) {
                return null;
            }
            return new String[] {TEST_FILE_NAME, TEST_FILE_NAME, TEST_FILE_NAME};
        }
    }
}

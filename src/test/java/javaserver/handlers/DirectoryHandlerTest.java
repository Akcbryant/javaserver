package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DirectoryHandlerTest {

    Request request = new Request();
    Response response;

    @Test
    public void createDirectoryListBody_GivenAnEmptyDirectory_ReturnsEmptyString() {
        response = new MockWithEmptyDirectory().handleRequest(request);

        assertEquals("", response.getBody());
    }

    @Test
    public void createDirectoryListBody_GivenANonEmptyDirectory_ReturnsHTMLListing() {
        response = new MockWithNonEmptyDirectory().handleRequest(request);

        String expected = String.format(DirectoryHandler.HTMLFORMAT, "/test", "test");
        assertEquals(expected + expected + expected, response.getBody());
    }

    private class MockWithEmptyDirectory extends DirectoryHandler {

        public MockWithEmptyDirectory() {
            super("");
        }

        @Override
        public String[] getFilesList(String directoryPath) {
            return null;
        }
    }

    private class MockWithNonEmptyDirectory extends DirectoryHandler {

        public MockWithNonEmptyDirectory() {
            super("");
        }

        @Override
        public String[] getFilesList(String directoryPath) {
            return new String[] {"test", "test", "test"};
        }
    }
}

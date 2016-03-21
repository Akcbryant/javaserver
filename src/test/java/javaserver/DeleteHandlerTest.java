package javaserver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.IOException;

import java.nio.file.Path;

public class DeleteHandlerTest {

    Request request = new Request();
    Response response;

    @Test
    public void deleteHandlerReturnsOKResponseWhenDeleteFileWorks() {
        response = new DeleteHandlerDeleteWorksMock().handleRequest(request);

        assertEquals("200", response.getStatusCode());
        assertEquals("OK", response.getStatusMessage());
    }

    @Test
    public void deleteHandlerReturnsNotFoundResponseWhenDeleteFileFails() {
        response = new DeleteHandlerDeleteFailsMock().handleRequest(request);

        assertEquals("404", response.getStatusCode());
        assertEquals("Not Found", response.getStatusMessage());
    }

    private class DeleteHandlerDeleteWorksMock extends DeleteHandler {

        @Override
        public void deleteFile(Path path) {

        }
    }

    private class DeleteHandlerDeleteFailsMock extends DeleteHandler {

        @Override
        public void deleteFile(Path path) throws IOException {
            throw new IOException();
        }
    }
}

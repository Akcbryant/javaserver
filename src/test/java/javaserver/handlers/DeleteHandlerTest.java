package javaserver.handlers;

import javaserver.Request;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.IOException;

import java.nio.file.Path;

public class DeleteHandlerTest {

    Request request = new Request();
    Response response;

    @Test
    public void deleteHandlerReturnsOKResponseWhenDeleteFileWorks() {
        response = new MockDeleteHandler(true).handleRequest(request);

        assertEquals(Status.Ok, response.getStatus());
    }

    @Test
    public void deleteHandlerReturnsNotFoundResponseWhenDeleteFileFails() {
        response = new MockDeleteHandler(false).handleRequest(request);

        assertEquals(Status.ServerError, response.getStatus());
    }

    private class MockDeleteHandler extends DeleteHandler {

        private boolean deleteIsSuccessful;

        MockDeleteHandler(boolean deleteIsSuccessful) {
            super("");
            this.deleteIsSuccessful = deleteIsSuccessful;
        }

        @Override
        public void deleteFile() throws IOException {
            if (deleteIsSuccessful) {

            } else {
                throw new IOException();
            }
        }
    }
}

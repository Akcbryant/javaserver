package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.FileUtility;
import javaserver.utility.ResourceUtility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.IOException;

import java.nio.file.Path;

public class PostPutHandlerTest {

    Request request = new Request();
    Response response;

    @Test
    public void successfulWriteToFileReturnsAnOkResponse() {
        response = new MockPostPutHandler(true).handleRequest(request);

        assertEquals(Status.Ok, response.getStatus());
    }

    @Test
    public void failureToWriteToFileReturnsAFailedResponse() {
        response = new MockPostPutHandler(false).handleRequest(request);

        assertEquals(Status.ServerError, response.getStatus());
    }

    private class MockPostPutHandler extends PostPutHandler {

        boolean writeDataIsSuccessful;

        MockPostPutHandler(boolean writeDataIsSuccessful) {
            super("", new FileUtility());
            this.writeDataIsSuccessful = writeDataIsSuccessful;
        }

        @Override
        public void writeData(String fileUri, byte[] data, ResourceUtility resourceUtility) throws IOException {
            if (writeDataIsSuccessful) {

            } else {
                throw new IOException();
            }
        }
    }
}

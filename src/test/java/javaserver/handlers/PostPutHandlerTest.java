package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.IOException;

import java.nio.file.Path;

public class PostPutHandlerTest {

    Request request = new Request();
    Response response;

    @Test
    public void successfulWriteToFileReturnsAnOkResponse() {
        response = new PostPutHandlerWriteDataSucceeds().handleRequest(request);

        assertEquals(Status.Ok, response.getStatus());
    }

    @Test
    public void failureToWriteToFileReturnsAFailedResponse() {
        response = new PostPutHandlerWriteDataFailed().handleRequest(request);

        assertEquals(Status.ServerError, response.getStatus());
    }

    private class PostPutHandlerWriteDataFailed extends PostPutHandler {

        @Override
        public void writeData(String dataString) throws IOException {
            throw new IOException();
        }
    }

    private class PostPutHandlerWriteDataSucceeds extends PostPutHandler {

        @Override
        public void writeData(String dataString) {

        }
    }
}

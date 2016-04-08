package javaserver;

import javaserver.handlers.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class WorkerTest {

    @Test
    public void runGetsHandlesAndRespondsWhenWriteSucceeds() {
        MockWorker mockWorker = new MockWorker(true);

        mockWorker.run();

        assertEquals(1, mockWorker.writeSuccesses);
    }

    @Test
    public void runClosesClientSocketWhenWriteFails() {
        MockWorker mockWorker = new MockWorker(false);

        mockWorker.run();

        assertTrue(mockWorker.socketIsClosed);
    }

    @Test
    public void runClosesClientSocketWhenWriteSucceeds() {
        MockWorker mockWorker = new MockWorker(true);

        mockWorker.run();

        assertTrue(mockWorker.socketIsClosed);
    }

    private class MockWorker extends Worker {

        public int writeSuccesses = 0;
        public boolean socketIsClosed = false;
        private boolean writeResponseSucceeds;

        MockWorker(boolean writeResponseSucceeds) {
            this.writeResponseSucceeds = writeResponseSucceeds;
            this.clientSocket = new MockSocket();
        }

        @Override
        protected void writeResponse(Response response) throws IOException {
            if (writeResponseSucceeds) {
                writeSuccesses++;
            }
            throw new IOException();
        }

        @Override
        protected Request getRequest(InputStream inputStream) {
            return new Request();
        }

        @Override
        protected Response handleRequest(Request request) {
            return new Response();
        }

        @Override
        protected void closeSocket() {
            socketIsClosed = true;
        }
    }

    private class MockSocket extends Socket {

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }
    }
}

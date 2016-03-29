package javaserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

import java.net.Socket;
import java.net.ServerSocket;

public class ServerTest {

    private static final int testPort = 5000;
    private static final Router testRouter = new Router("./");

    Server testServer = new Server(testPort, testRouter);

    @Test
    public void testPortIsSet() {
        assertEquals(testServer.port, testPort);
    }
}

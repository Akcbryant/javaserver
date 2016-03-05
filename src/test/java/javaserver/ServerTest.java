package javaserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerTest {

    int testPort = 5000;

    Server testServer;

    @Before
    public void setUp() throws Exception {
        testServer = new Server(testPort);
    }

    @Test
    public void testPortIsSet() {
        assertEquals("Failure - the port did not get set.", testServer.port, testPort);
    }
}

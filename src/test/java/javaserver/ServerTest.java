package javaserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerTest {

    int testPort = 5000;

    @Test
    public void testPortIsSet() {
        Server testServer = new Server(testPort);
        assertEquals("Failure - the port did not get set.", testServer.port, testPort);
    }

    @Test
    public void testServerStart() throws Exception {
        ServerSocket testServerSocket = new ServerSocket(testPort);

        Socket testClientSocket = new Socket("localhost", testPort);
        testClientSocket.getOutputStream().write("GET / HTTP/1.1\n".getBytes());
        Server testServer = new Server(testPort, testServerSocket, testClientSocket);

        testServer.start();
        assertEquals(testServer.clientSocket.getChannel(), testServer.serverSocket.getChannel());
    }

}

package javaserver;

import org.junit.Assert.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerTest extends TestCase {

    int testPort = 5000;

    public void testPortIsSet() {
        Server testServer = new Server(testPort);
        assertEquals("Failure - the port did not get set.", testServer.port, testPort);
    }

    public void testServerStart() throws Exception {
        ServerSocket testServerSocket = new ServerSocket(testPort);

        Socket testClientSocket = new Socket("localhost", testPort);
        testClientSocket.getOutputStream().write("GET / HTTP/1.1\n".getBytes());
        Server testServer = new Server(testPort, testServerSocket, testClientSocket);

        testServer.start();
        assertEquals(testServer.clientSocket.getChannel(), testServer.serverSocket.getChannel());
    }

}

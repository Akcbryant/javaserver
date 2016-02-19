package javaserver;

import junit.framework.Assert.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.net.Socket;
public class ServerTest extends TestCase {

    int testPort = 5000;
    Server server = new Server(testPort);

    public void testPortBound() {
        assertEquals("Failure - the port did not get set.", server.port, 5000);
    }

    //public void testStart() throws Exception {
       //server.start();

       //Socket mockClientSocket = new Socket("localhost", 5000);
       //mockClientSocket.getOutputStream().write("GET".getBytes());

       //assertTrue("Failure - your ServerSocket was not created", server.serverSocket != null);
       //assertEquals(mockClientSocket, server.clientSocket);

       //mockClientSocket.close();
    //}
}

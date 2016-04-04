package javaserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerTest {

    @Test
    public void acceptsRequestWhileServerIsOn() throws IOException {
        MockServerSocket serverSocket = new MockServerSocket();
        Server testServer = new Server(new Router(""), serverSocket);
        serverSocket.setServer(testServer);

        testServer.turnOn();

        assertEquals(1, serverSocket.acceptCalled);
    }

    private class MockServerSocket extends ServerSocket {

        private Server testServer;
        public int acceptCalled = 0;

        MockServerSocket() throws IOException {}

        @Override
        public Socket accept() {
            testServer.turnOff();
            acceptCalled++;
            return new Socket();
        }

        public void setServer(Server server) {
            this.testServer = server;
        }
    }
}

package javaserver;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {

    @Test
    public void serverIsTurnedOn() {
        MockServer mockServer = new MockServer();
        new App(mockServer).main(new String[0]);

        assertTrue(mockServer.turnedOn);
    }

    private class MockServer extends Server {

        boolean turnedOn = false;

        @Override
        public void turnOn() {
            turnedOn = true;
        }
    }
}

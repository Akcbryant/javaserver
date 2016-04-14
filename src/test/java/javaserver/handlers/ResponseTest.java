package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponseTest {

    private static final String EXPECTED = "HTTP/1.1 200 OK\r\n\r\n";
    private static final String TEST_STRING = "test";

    private Response response = new Response("HTTP/1.1", Status.Ok, "", "");

    @Test
    public void toStringConversionGivesProperFormat() {
        assertEquals(EXPECTED, response.toString());
    }

    @Test
    public void getBytesReturnsByteArrayOfResponseWhenEverythingIsString() {
        String responseString = new String(response.getBytes());
        assertEquals(EXPECTED, responseString);
    }

    @Test
    public void getBytesReturnsByteArrayOfResponseWhenBodyIsByteArray() throws IOException {
        byte[] testByteArray = TEST_STRING.getBytes();
        response.setBody(testByteArray);

        String responseString = new String(response.getBytes());
        assertEquals(EXPECTED + TEST_STRING, responseString);
    }
}

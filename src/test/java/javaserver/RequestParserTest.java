package javaserver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.*;

public class RequestParserTest {

    String firstLine = "GET / HTTP/1.1\r\n";
    ByteArrayInputStream testInput = new ByteArrayInputStream(firstLine.getBytes());
    Request request = new RequestParser().parseRequest(testInput);

    @Test
    public void testInput() {
        assertEquals("Request method not properly set.", request.getMethod(), "GET");
        assertEquals("Request path not properly set.", request.getPath(), "/");
        assertEquals("Request version not properly set.", request.getVersion(), "HTTP/1.1");
    }
}

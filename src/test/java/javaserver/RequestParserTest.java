package javaserver;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.io.*;

public class RequestParserTest {

    String firstLine = "GET / HTTP/1.1\r\n";
    String headers = "Accept: audio/*; q=0.2, audio/basic\n" +
                     "Allow: GET, HEAD, PUT\n" +
                     "Expires: Thu, 01 Dec 1994 16:00:00 GMT\n" +
                     "From: testing@headers.org\r\n";
    String body = "this is test body data\n";
    String testString = firstLine + headers + body;

    RequestParser parser = createTestParser(testString);

    @Test
    public void testMethodIsParsed() {
        assertEquals("Request method not properly parsed.", "GET", parser.method);
    }

    @Test
    public void testPathIsParsed() {
        assertEquals("Request path not properly parsed.", "/", parser.path);
    }

    @Test
    public void testVersionIsParsed() {
        assertEquals("Request version not properly parsed.", "HTTP/1.1", parser.version);
    }


    @Test
    public void testHeadersAreParsed() {
        assertEquals("Headers are not properly parsed", 4, parser.headers.size());
    }

    @Test
    public void testBodyIsParsed() {
        assertEquals("Body is not parsed properly.", body, parser.body);
    }

    @Test
    public void testEmptyInput() {
        parser = createTestParser("");
        assertEquals("An empty request makes everything empty.", "", parser.method);
    }

    @Test
    public void testEmptyBody() {
        testString = firstLine + headers;
        parser = createTestParser(testString); 
        assertFalse("First line should be set.", parser.method.isEmpty());
        assertTrue("Body should be empty.", parser.body.isEmpty());
    }

    @Test
    public void testEmptyHeadersAndBody() {
        testString = firstLine;
        parser = createTestParser(testString);
        assertFalse("First line should be set.", parser.version.isEmpty());
        assertTrue("Headers should be empty.", parser.headers.isEmpty());
        assertTrue("Body should be empty.", parser.body.isEmpty());
    }

    private RequestParser createTestParser(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        RequestParser parser = new RequestParser(inputStream);
        return parser;
    }
}

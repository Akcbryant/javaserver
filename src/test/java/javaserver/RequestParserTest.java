package javaserver;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.io.*;

public class RequestParserTest {

    private static final String firstLine = "GET / HTTP/1.1\r\n";
    private static final String headers = "Accept: audio/*; q=0.2, audio/basic\n" +
                     "Allow: GET, HEAD, PUT\n" +
                     "Expires: Thu, 01 Dec 1994 16:00:00 GMT\n" +
                     "From: testing@headers.org\r\n";
    private static final String body = "this is test body data\n";
    private String testString = firstLine + headers + body;

    Request request = createTestRequest(testString);

    @Test
    public void methodIsParsed() {
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void pathIsParsed() {
        assertEquals("Request path not properly parsed.", "/", request.getPath());
    }

    @Test
    public void versionIsParsed() {
        assertEquals("Request version not properly parsed.", "HTTP/1.1", request.getVersion());
    }


    @Test
    public void headersAreParsed() {
        assertEquals("Headers are not properly parsed", 4, request.getHeaders().size());
    }

    @Test
    public void bodyIsParsed() {
        assertEquals("Body is not parsed properly.", body, request.getBody());
    }

    @Test
    public void emptyInput() {
        request = createTestRequest("");

        assertEquals("An empty request makes everything empty.", "", request.getMethod());
    }

    @Test
    public void emptyBody() {
        testString = firstLine + headers;
        request = createTestRequest(testString);

        assertFalse("First line should be set.", request.getMethod().isEmpty());
        assertTrue("Body should be empty.", request.getBody().isEmpty());
    }

    @Test
    public void emptyHeadersAndBody() {
        testString = firstLine;
        request = createTestRequest(testString);

        assertFalse("First line should be set.", request.getVersion().isEmpty());
        assertTrue("Headers should be empty.", request.getHeaders().isEmpty());
        assertTrue("Body should be empty.", request.getBody().isEmpty());
    }

    private Request createTestRequest(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Request request = new RequestParser().parseRequest(inputStream);
        return request;
    }
}

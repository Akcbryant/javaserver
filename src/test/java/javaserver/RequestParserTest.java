package javaserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayInputStream;

public class RequestParserTest {

    private static final String firstLine = "GET / HTTP/1.1\r\n";
    private static final String headers = "Accept: audio/*; q=0.2, audio/basic\r\n" +
                                          "Allow: GET, HEAD, PUT\r\n" +
                                          "Expires: Thu, 01 Dec 1994 16:00:00 GMT\r\n" +
                                          "From: testing@headers.org\r\n\r\n";
    private static final String body = "this is test body data\r\n";
    private String testString = firstLine + headers + body;

    Request request = createTestRequest(testString);

    @Test
    public void methodIsParsed() {
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void uriIsParsed() {
        assertEquals("/", request.getUri());
    }

    @Test
    public void versionIsParsed() {
        assertEquals("HTTP/1.1", request.getVersion());
    }


    @Test
    public void headersAreParsed() {
        assertEquals(4, request.getHeaders().size());
    }

    @Test
    public void bodyIsParsed() {
        assertEquals(body, request.getBody());
    }

    @Test
    public void emptyInput() {
        request = createTestRequest("");

        assertEquals("", request.getMethod());
    }

    @Test
    public void emptyBody() {
        testString = firstLine + headers;
        request = createTestRequest(testString);

        assertFalse(request.getMethod().isEmpty());
        assertTrue(request.getBody().isEmpty());
    }

    @Test
    public void emptyHeadersAndBody() {
        testString = firstLine;
        request = createTestRequest(testString);

        assertFalse(request.getVersion().isEmpty());
        assertTrue(request.getHeaders().isEmpty());
        assertTrue(request.getBody().isEmpty());
    }

    private Request createTestRequest(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Request request = new RequestParser().parseRequest(inputStream);
        return request;
    }
}

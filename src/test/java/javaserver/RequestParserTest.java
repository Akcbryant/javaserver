package javaserver;

import static org.junit.Assert.assertEquals;
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
    ByteArrayInputStream testInput = new ByteArrayInputStream(testString.getBytes());

    RequestParser parser = new RequestParser(testInput);

    @Test
    public void testFirstLineIsParsed() {
        assertEquals("Request method not properly set.", "GET", parser.method);
        assertEquals("Request path not properly set.", "/", parser.path);
        assertEquals("Request version not properly set.", "HTTP/1.1", parser.version);
    }

    @Test
    public void testHeadersAreParsed() {
        assertEquals("Headers are not parsed properly", 4, parser.headers.size());
    }

    @Test
    public void testBodyIsParsed() {
        assertEquals("Body is not parsed properly", body, parser.body);
    }

    @Test
    public void testEmptyInput() {
        ByteArrayInputStream emptyInput = new ByteArrayInputStream("".getBytes());
        RequestParser emptyParser = new RequestParser(emptyInput);
        assertEquals("Deal with empty request", "", emptyParser.method);
    }

    @Test
    public void testEmptyBody() {

    }
}

package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResponseTest {

    @Test
    public void toStringConversion() {
        Response response = new Response("HTTP/1.1", Status.Ok, "", "");

        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.toString());
    }

}

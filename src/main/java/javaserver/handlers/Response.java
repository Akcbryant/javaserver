package javaserver.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Response {

    private static final String RESPONSE_NEWLINE = "\r\n";

    private String version = "HTTP/1.1";
    private Status status = Status.EMPTY;
    private String headers = "";
    private String body = "";
    private byte[] byteBody = new byte[0];

    public Response() {

    }

    Response(String version, Status status, String headers, String body) {
        this.version = version;
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public String getVersion() {
        return version;
    }

    public Status getStatus() {
        return status;
    }

    public String getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public byte[] getByteBody() {
        return byteBody;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setBody(byte[] byteBody) {
        this.byteBody = byteBody;
    }

    public byte[] getBytes() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            byteStream.write(toString().getBytes());
            byteStream.write(byteBody);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return byteStream.toByteArray();
    }

    public String toString() {
        return version + " " + status + RESPONSE_NEWLINE + headers + RESPONSE_NEWLINE + body;
    }
}

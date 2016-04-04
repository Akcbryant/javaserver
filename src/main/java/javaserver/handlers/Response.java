package javaserver.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Response {

    private String version = "HTTP/1.1";
    private Status status = Status.Empty;
    private String headers = "";
    private String body = "";
    private byte[] byteBody = new byte[0];

    Response() {

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

    public String toString() {
        return version + " " + status + "\r\n" + headers + "\r\n" + body;
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
}

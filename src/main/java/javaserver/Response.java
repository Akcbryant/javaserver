package javaserver;

import java.util.HashMap;

public class Response {

    private String version = "HTTP/1.1";
    private String statusCode = "";
    private String statusMessage = "";
    private String headers = "";
    private String body = "";

    Response() {

    }

    Response(String version, String statusCode, String statusMessage, String headers, String body) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.headers = headers;
        this.body = body;
    }

    public String getVersion() {
        return version;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String headers() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toString() {
        return version + " " + statusCode + " " + statusMessage + "\r\n" + headers + "\r\n" + body + "\r\n";
    }

}

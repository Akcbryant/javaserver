package javaserver;

import java.util.HashMap;

public class Response {

    private String version = "";
    private String statusCode = "";
    private String statusMessage = "";
    private HashMap<String, String> headers = new HashMap<String, String>();
    private String body = "";

    Response(Request request) {

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

    public HashMap<String, String> getHeaders() {
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

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

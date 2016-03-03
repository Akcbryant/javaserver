package javaserver;

import java.util.HashMap;

public class Request {

    private String method = "";
    private String path = "";
    private String version = "";
    private HashMap<String, String> headers;
    private String body = "";

    Request(String method, String path, String version) {
        this.method = method;
        this.path = path;
        this.version = version;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

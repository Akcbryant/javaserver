package javaserver;

import java.util.HashMap;

public class Request {

    private String method = "";
    private String path = "";
    private String version = "";
    private HashMap<String, String> headers = new HashMap<String, String>();
    private String body = "";

    Request() {

    }

    Request(String method, String path, String version,
            HashMap<String, String> headers,
            String body) {
        this.method = method;
        this.path = path;
        this.version = version;
        this.headers = headers;
        this.body = body;
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

    public void setVersion(String version) {
        this.version = version;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toString() {
        return method + " " + path + " " + version + "\r\n" + headers.toString() + "\r\n" + body + "\r\n";
    }
}

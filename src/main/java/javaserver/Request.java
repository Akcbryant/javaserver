package javaserver;

import java.util.HashMap;

public class Request {

    protected static final String NEW_LINE = "\r\n";
    protected static final String SPACE = " ";

    private String method = "";
    private String uri = "";
    private String parameters = "";
    private String version = "";
    private HashMap<String, String> headers = new HashMap<String, String>();
    private String body = "";

    public Request() {
    }

    public Request(String method, String uri, String parameters, String version,
            HashMap<String, String> headers,
            String body) {
        this.method = method;
        this.uri = uri;
        this.parameters = parameters;
        this.version = version;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getParameters() {
        return parameters;
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

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
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

    public String getFirstLine() {
        return method + SPACE + uri + SPACE + version + NEW_LINE;
    }

    public String toString() {
        return method + SPACE + uri + SPACE + version + NEW_LINE + headers.toString() + NEW_LINE + body + NEW_LINE;
    }
}

package javaserver;

public class Request {

    private String method;
    private String path;
    private String version;

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
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

    Request(String method, String path, String version) {
        this.method = method;
        this.path = path;
        this.version = version;
    }
}

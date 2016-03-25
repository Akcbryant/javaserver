package javaserver;

public enum Status {

    Empty(""),
    Ok("200 OK"),
    Redirect("302 Redirect"),
    NotFound("404 Not Found"),
    MethodNotAllowed("405 Method not allowed");

    private final String statusMessage;

    Status(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String toString() {
        return this.statusMessage;
    }
}

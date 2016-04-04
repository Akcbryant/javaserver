package javaserver.handlers;

public enum Status {

    Empty(""),
    Ok("200 OK"),
    NoContent("204 No Content"),
    PartialContent("206 Partial Content"),
    Redirect("302 Redirect"),
    NotFound("404 Not Found"),
    MethodNotAllowed("405 Method Not Allowed"),
    ServerError("500 Internal Server Error");

    private final String statusMessage;

    Status(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String toString() {
        return this.statusMessage;
    }
}

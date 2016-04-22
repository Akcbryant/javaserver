package javaserver.handlers;

public enum Status {

    EMPTY(""),
    OK("200 OK"),
    NO_CONTENT("204 No Content"),
    PARTIAL_CONTENT("206 Partial Content"),
    REDIRECT("302 Redirect"),
    UNAUTHORIZED("401 Unauthorized"),
    NOT_FOUND("404 Not Found"),
    METHOD_NOT_ALLOWED("405 Method Not Allowed"),
    SERVER_ERROR("500 Internal Server Error");

    private final String statusMessage;

    Status(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String toString() {
        return this.statusMessage;
    }
}

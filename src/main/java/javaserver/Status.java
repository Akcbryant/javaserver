package javaserver;

public enum Status {

    EMPTY(""),
    OK("200 OK"),
    NOTFOUND("404 Not Found");

    private final String statusMessage;

    Status(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String toString() {
        return this.statusMessage;
    }
}

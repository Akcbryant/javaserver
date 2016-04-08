package javaserver;

import javaserver.handlers.Handler;
import javaserver.handlers.FileHandler;

public class Route {

    String uri;
    String method;
    Handler handler;

    Route(String uri, String method, Handler handler) {
        this.uri = uri;
        this.method = method;
        this.handler = handler;
    }

    Route(String uri, String method) {
        this.uri = uri;
        this.method = method;
    }

    public String toString() {
        return uri + " " + method;
    }
}

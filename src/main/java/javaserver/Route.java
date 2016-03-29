package javaserver;

import javaserver.handlers.Handler;

public class Route {

    String uri;
    String method;
    Handler handler;

    Route(String uri, String method, Handler handler) {
        this.uri = uri;
        this.method = method;
        this.handler = handler;
    }
}

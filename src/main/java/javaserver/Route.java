package javaserver;

import javaserver.handlers.Handler;
import javaserver.handlers.FileHandler;

public class Route {

    private String uri;
    private String method;
    private Handler handler;

    public Route(String method, String uri, Handler handler) {
        this.method = method;
        this.uri = uri;
        this.handler = handler;
    }

    Route(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public Handler getHandler() {
        return handler;
    }

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public String toString() {
        return uri + " " + method;
    }

    @Override
    public boolean equals(Object obj) {
        Route route = (Route)obj;

        if (route == null) {
            return false;
        } else if (method.equals(route.method) && uri.equals(route.uri)) {
            return true;
        }
        return false;
    }
}

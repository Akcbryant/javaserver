package javaserver;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import javaserver.handlers.Handler;

public class Router {

    private String rootPath;
    private List<Route> routes = new ArrayList<Route>();

    public Router(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public boolean hasRoute(String uri) {
        for (Route route : routes) {
            if (route.uri.equals(uri)) {
                return true;
            }
        }
        return false;
    }

    public Handler getHandler(String uri, String method) {
        for (Route route : routes) {
            if (route.uri.equals(uri) && route.method.equals(method)) {
                return route.handler;
            }
        }
        return null;
    }

    public String availableMethods(String uri) {
        String availableMethods = "";

        for (Route route : routes) {
            if (route.uri.equals(uri)) {
                availableMethods += route.method + ",";
            }
        }

        availableMethods += "OPTIONS";

        return availableMethods;
    }

    private String stripLastLetter(String string) {
        if (string != null && string.length() > 0) {
            string = string.substring(0, string.length()-1);
        }
        return string;
    }
}

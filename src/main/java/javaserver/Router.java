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

    public boolean hasUri(Route route) {
        for (Route routerRoute : routes) {
            if (routerRoute.getUri().equals(route.getUri())) {
                return true;
            }
        }
        return false;
    }

    public Handler getHandler(Route route) {
        for (Route routerRoute : routes) {
            if (routerRoute.equals(route)) {
               return routerRoute.getHandler();
            }
        }
        return null;
    }

    public String availableMethods(String uri) {
        String availableMethods = "";

        for (Route route : routes) {
            String routeUri = route.getUri();
            if (routeUri.equals(uri)) {
                availableMethods += route.getMethod() + ",";
            }
        }

        availableMethods += "OPTIONS";

        return availableMethods;
    }
}

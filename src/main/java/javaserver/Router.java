package javaserver;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

public class Router {

    private HashMap<String, String[]> routes;
    private HashMap<String, String> redirects;

    Router(Routes routes) {
        this.routes = routes.routes;
        this.redirects = routes.redirects;
    }

    public boolean uriIsAllowed(String method, String uri) {
        String[] availableRoutes = routes.get(method);
        uri = uri.split("\\?")[0];

        if (availableRoutes != null) {
            List routesList = Arrays.asList(availableRoutes);
            return routesList.contains(uri);
        } else {
            return false;
        }
    }

    public String availableMethods(String uri) {
        String availableMethods = "";

        for (Map.Entry<String, String[]> entry : routes.entrySet()) {
            if (Arrays.asList(entry.getValue()).contains(uri)) {
                availableMethods += entry.getKey() + ",";
            }
        }

        return stripLastLetter(availableMethods);
    }

    public boolean isRedirect(String uri) {
        return redirects.containsKey(uri);
    }

    public String getRedirectHeader(String uri) {
        if (isRedirect(uri)) {
            return "Location: " + redirects.get(uri);
        }
        return "";
    }

    private String stripLastLetter(String string) {
        if (string != null && string.length() > 0) {
            string = string.substring(0, string.length()-1);
        }
        return string;
    }
}

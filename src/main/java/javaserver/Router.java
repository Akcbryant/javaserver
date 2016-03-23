package javaserver;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

public class Router {
    private HashMap<String, String[]> routes;

    Router() {
        routes = new HashMap<String, String[]>();
        routes.put("GET", new String[]{"/", "/form", "/method_options", "/parameters"});
        routes.put("HEAD", new String[]{"/method_options"});
        routes.put("POST", new String[]{"/form", "/method_options"});
        routes.put("OPTIONS", new String[]{"/", "/form", "/method_options"});
        routes.put("PUT", new String[]{"/form", "/method_options"});
        routes.put("DELETE", new String[]{"/form"});
    }

    Router(HashMap<String, String[]> routes) {
        this.routes = routes;
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

    private String stripLastLetter(String string) {
        if (string != null && string.length() > 0) {
            string = string.substring(0, string.length()-1);
        }
        return string;
    }
}

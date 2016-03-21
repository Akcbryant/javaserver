package javaserver;

import java.util.Arrays;
import java.util.List;

public class Router {

    private final static List availablePaths = Arrays.asList("/", "/form");

    public boolean pathIsAllowed(String route) {
        return availablePaths.contains(route);
    }
}

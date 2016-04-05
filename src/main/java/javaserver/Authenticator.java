package javaserver;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Base64;

public class Authenticator {

    private ArrayList<Route> protectedRoutes = new ArrayList<Route>();
    private ArrayList<String> authenticatedUsers = new ArrayList<String>();

    public void addToProtectedRoutes(Route route) {
        protectedRoutes.add(route);
    }

    public void addAuthenticatedUser(String credentials) {
        authenticatedUsers.add(credentials);
    }

    public boolean isRouteProtected(Route route) {
        for (Route currentRoute : protectedRoutes) {
            if (route.toString().equals(currentRoute.toString())) return true;
        }
        return false;
    }

    public boolean isRequestAuthenticated(Request request) {
        HashMap <String, String> headers = request.getHeaders();

        if (headers.containsKey("Authorization")) {
            String auth = headers.get("Authorization");
            String encodedCredentials = auth.split(" ")[1];
            String decodedCredentials = decodeString(encodedCredentials);

            System.out.println(auth + " " + encodedCredentials + " " + decodedCredentials);
            return isUserAuthenticated(decodedCredentials);
        }

        return false;
    }

    protected boolean isUserAuthenticated(String credentials) {
        return authenticatedUsers.contains(credentials);
    }

    protected String decodeString(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }
}

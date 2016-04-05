package javaserver;

import javaserver.handlers.FileHandler;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AuthenticatorTest {

    Authenticator authenticator = new Authenticator();
    Route route;
    Request request = new Request();
    String testBase64 = "dGVzdGluZ1Rlc3Q=";
    String expectedString = "testingTest";

    @Test
    public void returnsFalseIfRouteIsNotProtected() {
        assertFalse(authenticator.isRouteProtected(route));
    }

    @Test
    public void returnsTrueIfRouteIsProtected() {
        route = new Route("/foo", "GET");
        authenticator.addToProtectedRoutes(route);

        Route testRoute = new Route("/foo", "GET");
        assertTrue(authenticator.isRouteProtected(testRoute));
    }

    @Test
    public void returnsTrueIfUsernameAndPasswordMatch() {
        authenticator.addAuthenticatedUser("test:subject");

        assertTrue(authenticator.isUserAuthenticated("test:subject"));
    }

    @Test
    public void returnsFalseIfCredentialsDoNotMatch() {
        assertFalse(authenticator.isUserAuthenticated("not:aUser"));
    }

    @Test
    public void decodesBase64EncodedString() {
        assertEquals(expectedString, authenticator.decodeString(testBase64));
    }

    @Test
    public void givenWrongCredentialsIsRequestAuthenticatedReturnsFalse() {
        assertFalse(authenticator.isRequestAuthenticated(request));
    }

    @Test
    public void givenCorrectCredentialsReturnsTrue() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Basic " + testBase64);

        authenticator.addAuthenticatedUser("testingTest");
        request.setHeaders(headers);

        assertTrue(authenticator.isRequestAuthenticated(request));
    }
}

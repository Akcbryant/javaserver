package javaserver;

import javaserver.handlers.FileHandler;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AuthenticatorTest {

    private static final Route PROTECTED_ROUTE = new Route("/foo", "GET");
    private static final String BASIC = "Basic ";
    private static final String BASE_64 = "dGVzdGluZ1Rlc3Q=";
    private static final String TEST_STRING = "testingTest";
    private static final String USER = "test:subject";

    private Authenticator authenticator = new Authenticator();
    private Route route;
    private Request request = new Request();

    @Test
    public void returnsFalseIfRouteIsNotProtected() {
        assertFalse(authenticator.isRouteProtected(PROTECTED_ROUTE));
    }

    @Test
    public void returnsTrueIfRouteIsProtected() {
        authenticator.addToProtectedRoutes(PROTECTED_ROUTE);

        assertTrue(authenticator.isRouteProtected(PROTECTED_ROUTE));
    }

    @Test
    public void returnsTrueIfUsernameAndPasswordMatch() {
        authenticator.addAuthenticatedUser(USER);

        assertTrue(authenticator.isUserAuthenticated(USER));
    }

    @Test
    public void returnsFalseIfCredentialsDoNotMatch() {
        assertFalse(authenticator.isUserAuthenticated("not:aUser"));
    }

    @Test
    public void decodesBase64EncodedString() {
        assertEquals(TEST_STRING, authenticator.decodeString(BASE_64));
    }

    @Test
    public void givenWrongCredentialsIsRequestAuthenticatedReturnsFalse() {
        assertFalse(authenticator.isRequestAuthenticated(request));
    }

    @Test
    public void givenCorrectCredentialsReturnsTrue() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(authenticator.AUTH_STRING, BASIC + BASE_64);

        authenticator.addAuthenticatedUser(TEST_STRING);
        request.setHeaders(headers);

        assertTrue(authenticator.isRequestAuthenticated(request));
    }
}

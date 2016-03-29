package javaserver;

import javaserver.handlers.Handler;
import javaserver.handlers.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

public class RouterTest {

    Router router = new Router(".");

    @Before
    public void setUp() {
        router.addRoute(new Route("/form", "GET", new MockHandler()));
        router.addRoute(new Route("/form", "POST", new MockHandler()));
    }

    @Test
    public void addRoute_GivenRoute_addsRoute() {
        assertTrue(router.hasRoute("/form"));
    }

    @Test
    public void isValidUri_GivenInvalidRoute_False() {
        assertFalse(router.hasRoute("/foobar"));
    }

    @Test
    public void isValidUri_GivenValideRoute_True() {
        assertTrue(router.hasRoute("/form"));
    }

    @Test
    public void availableMethods_GivenValidRoute_ReturnsStringOfMethods() {
        String availableMethods = router.availableMethods("/form");

        assertEquals("GET,POST,OPTIONS", availableMethods);
    }

    @Test
    public void availableMethods_GivenRouteWithNoMethods_ReturnsEmptyString() {
        String availableMethods = router.availableMethods("failure");

        assertEquals("OPTIONS", availableMethods);
    }

    private class MockHandler implements Handler {

        public Response handleRequest(Request request) {
            return null;
        }
    }
}

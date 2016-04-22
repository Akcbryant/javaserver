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
    public void returnTrueIfRouteExistsInRouter() {
        assertTrue(router.hasRoute("/form"));
    }

    @Test
    public void returnFalseIfRouteDoesNotExistInRouter() {
        assertFalse(router.hasRoute("/foobar"));
    }

    @Test
    public void returnAvailableMethodsForARoute() {
        String availableMethods = router.availableMethods("/form");

        assertEquals("GET,POST,OPTIONS", availableMethods);
    }

    @Test
    public void returnOnlyOptionsGivenARouteWithNoAditionalMethods() {
        String availableMethods = router.availableMethods("failure");

        assertEquals("OPTIONS", availableMethods);
    }

    private class MockHandler implements Handler {

        public Response handleRequest(Request request) {
            return null;
        }
    }
}

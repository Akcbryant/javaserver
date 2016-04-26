package javaserver;

import javaserver.handlers.Handler;
import javaserver.handlers.MethodNotAllowedHandler;
import javaserver.handlers.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

public class RouterTest {

    private Router router = new Router(".");
    private static final Route GET_ROUTE = new Route("GET", "/form");
    private static final Route POST_ROUTE = new Route("POST", "/form");
    private static final Route GET_HANDLER_ROUTE = new Route("GET", "/test", new MethodNotAllowedHandler());

    @Before
    public void setUp() {
        router.addRoute(GET_ROUTE);
        router.addRoute(POST_ROUTE);
        router.addRoute(GET_HANDLER_ROUTE);
    }

    @Test
    public void returnTrueIfRouteExistsInRouter() {
        assertTrue(router.hasUri(GET_ROUTE));
    }

    @Test
    public void returnFalseIfRouteDoesNotExistInRouter() {
        Route fakeRoute = new Route("GET", "/foobar");
        assertFalse(router.hasUri(fakeRoute));
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

    @Test
    public void returnHandlerAssociatedWithGivenRoute() {
        Route testRoute = new Route("GET", "/test");
        Handler handler = router.getHandler(testRoute);

        assertEquals(new MethodNotAllowedHandler().getClass(), handler.getClass());
    }
}

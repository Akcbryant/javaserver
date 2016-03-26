package javaserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RouterTest {

    private Router router = new Router(new CobSpecRoutes());

    @Test
    public void getMethodIsAllowedOnRootRoute() {
        assertTrue(router.uriIsAllowed("GET", "/"));
    }

    @Test
    public void deleteMethodIsNotAllowedOnRootRoute() {
        assertFalse(router.uriIsAllowed("DELETE", "/"));
    }

    @Test
    public void passingInWrongMethodReturnsFalse() {
        assertFalse(router.uriIsAllowed("FOOBAR", "/"));
    }

    @Test
    public void givenRootDirectoryReturnAvailableMethods() {
        assertEquals("GET,OPTIONS", router.availableMethods("/"));
    }

    @Test
    public void isRedirect_GivenRedirectUri_True() {
        assertTrue(router.isRedirect("/redirect"));
    }

    @Test
    public void isRedirect_GivenNonRedirectUri_False() {
        assertFalse(router.isRedirect("/"));
    }

    @Test
    public void redirectRoute_GivenRedirectUri_route() {
        assertEquals("Location: http://localhost:5000/", router.getRedirectHeader("/redirect"));
    }

    @Test
    public void getRedirectRoute_GivenNonRedirectUri_EmptyString() {
        assertEquals("", router.getRedirectHeader("/"));
    }
}

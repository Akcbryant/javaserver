package javaserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RouterTest {

    private Router router = new Router();

    @Test
    public void getMethodIsAllowedOnRootRoute() {
        assertTrue(router.pathIsAllowed("GET", "/"));
    }

    @Test
    public void deleteMethodIsNotAllowedOnRootRoute() {
        assertFalse(router.pathIsAllowed("DELETE", "/"));
    }

    @Test
    public void passingInWrongMethodReturnsFalse() {
        assertFalse(router.pathIsAllowed("FOOBAR", "/"));
    }

    @Test
    public void givenRootDirectoryReturnAvailableMethods() {
        assertEquals("GET,OPTIONS", router.availableMethods("/"));
    }
}
package javaserver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class RouteTest {

    Route route = new Route("GET", "/test");
    Route sameRoute = new Route("GET", "/test");
    Route differentRoute = new Route("POST", "/wrong");

    @Test
    public void returnTrueWhenEqualsIsCalledOnTheSameRouteObject() {
        assertTrue(route.equals(route));
    }

    @Test
    public void returnTrueWhenEqualsIsCalledOnSimilarRoutesObjects() {
        assertTrue(route.equals(sameRoute));
    }

    @Test
    public void returnFalseWhenEqualsIsCalledWithTwoDifferentObjects() {
        assertFalse(route.equals(differentRoute));
    }
}

package javaserver;

import javaserver.handlers.DeleteHandler;
import javaserver.handlers.DirectoryHandler;
import javaserver.handlers.FileHandler;
import javaserver.handlers.Handler;
import javaserver.handlers.HeadHandler;
import javaserver.handlers.MethodNotAllowedHandler;
import javaserver.handlers.OptionsHandler;
import javaserver.handlers.ParametersHandler;
import javaserver.handlers.PostPutHandler;
import javaserver.handlers.RedirectHandler;
import javaserver.handlers.Response;
import javaserver.handlers.UnauthorizedHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;

public class RequestHandlerTest {

    private Authenticator authenticator;
    private Router router;
    private RequestHandler requestHandler;
    private Response response;
    private Request request;
    private Handler handler;

    private Handler determineHandler(String method, String uri) {
        request = new Request();
        request.setMethod(method);
        request.setUri(uri);
        return requestHandler.determineHandler(request);
    }

    @Before
    public void setUp() {
        Router testRouter = new Router(".");
        testRouter.addRoute(new Route("/form", "GET", new FileHandler("")));
        testRouter.addRoute(new Route("/form", "DELETE", new DeleteHandler("")));
        testRouter.addRoute(new Route("/method_options", "PUT", new PostPutHandler("")));
        testRouter.addRoute(new Route("/method_options", "POST", new PostPutHandler("")));
        testRouter.addRoute(new Route("/method_options", "OPTIONS", new OptionsHandler("")));
        testRouter.addRoute(new Route("/parameters", "GET", new ParametersHandler()));
        testRouter.addRoute(new Route("/redirect", "GET", new RedirectHandler("")));
        testRouter.addRoute(new Route("/logs", "GET", new FileHandler("")));

        authenticator = new Authenticator();
        authenticator.addToProtectedRoutes(new Route("/logs", "GET"));

        requestHandler = new RequestHandler(testRouter, authenticator);
    }

    @Test
    public void handleRequestAlwaysReturnAResponse() {
        request = new Request();

        response = requestHandler.handleRequest(request);

        assertNotNull(response);
    }

    @Test
    public void returnDirectoryHandlerFromAGetToANonCustomRouteThatIsADirectory() {
        handler = determineHandler("GET", "/");

        assertEquals(new DirectoryHandler("").getClass(), handler.getClass());
    }

    @Test
    public void postToCustomRouteReturnsPostPutHandler() {
        handler = determineHandler("POST", "/method_options");

        assertEquals(new PostPutHandler("").getClass(), handler.getClass());
    }

    @Test
    public void putToCustomDefinedRouteReturnsPostPutHandler() {
        handler = determineHandler("PUT", "/method_options");

        assertEquals(new PostPutHandler("").getClass(), handler.getClass());
    }

    @Test
    public void deleteToCustomDefinedRouteReturnsDeleteHandler() {
        handler = determineHandler("DELETE", "/form");

        assertEquals(new DeleteHandler("").getClass(), handler.getClass());
    }

    @Test
    public void optionsRequestOnlyWorksWhenCustomizedOnARoute() {
        handler = determineHandler("OPTIONS", "/method_options");

        assertEquals(new OptionsHandler("").getClass(), handler.getClass());
    }

    @Test
    public void determineHandler_GivenParametersURI_ReturnsParametersHandler() {
        handler = determineHandler("GET", "/parameters?variable1=%20%3C%2C%20&variable2=stuff");

        assertEquals(new ParametersHandler().getClass(), handler.getClass());
    }

    @Test
    public void getRedirectReturnsRedirectHandler() {
        handler = determineHandler("GET", "/redirect");

        assertEquals(new RedirectHandler("").getClass(), handler.getClass());
    }

    @Test
    public void unallowedMethodReturnsNotAllowedHandler() {
        handler = determineHandler("GET", "/method_options");

        assertEquals(new MethodNotAllowedHandler().getClass(), handler.getClass());
    }

    @Test
    public void protectedRouteReturnsUnavailableHandlerIfRequestIsUnauthorized() {
        handler = determineHandler("GET", "/logs");

        assertEquals(new UnauthorizedHandler("").getClass(), handler.getClass());
    }

    @Test
    public void protectedRouteReturnsItsGivenHandlerIfRequestIsAuthorized() {
        authenticator.addAuthenticatedUser("username:password");

        request = new Request();
        request.setMethod("GET");
        request.setUri("/logs");

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ=");
        request.setHeaders(headers);

        handler = requestHandler.determineHandler(request);

        assertEquals(new FileHandler("").getClass(), handler.getClass());
    }
}

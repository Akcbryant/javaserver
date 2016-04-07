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
import javaserver.utility.FileUtility;

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

    private static final FileUtility UTILITY = new FileUtility();
    private static final FileHandler FILE_HANDLER = new FileHandler("", UTILITY);
    private static final DeleteHandler DELETE_HANDLER = new DeleteHandler("", UTILITY);
    private static final DirectoryHandler DIRECTORY_HANDLER = new DirectoryHandler("");
    private static final PostPutHandler POST_PUT_HANDLER = new PostPutHandler("", UTILITY);
    private static final OptionsHandler OPTIONS_HANDLER = new OptionsHandler("");
    private static final ParametersHandler PARAMETERS_HANDLER = new ParametersHandler();
    private static final RedirectHandler REDIRECT_HANDLER = new RedirectHandler("");
    private static final MethodNotAllowedHandler METHOD_NOT_ALLOWED_HANDLER = new MethodNotAllowedHandler();
    private static final UnauthorizedHandler UNAUTHORIZED_HANDLER = new UnauthorizedHandler("", UTILITY);

    @Before
    public void setUp() {
        Router testRouter = new Router(".");
        testRouter.addRoute(new Route("/form", "GET", FILE_HANDLER));
        testRouter.addRoute(new Route("/form", "DELETE", DELETE_HANDLER));
        testRouter.addRoute(new Route("/method_options", "PUT", POST_PUT_HANDLER));
        testRouter.addRoute(new Route("/method_options", "POST", POST_PUT_HANDLER));
        testRouter.addRoute(new Route("/method_options", "OPTIONS", OPTIONS_HANDLER));
        testRouter.addRoute(new Route("/parameters", "GET", PARAMETERS_HANDLER));
        testRouter.addRoute(new Route("/redirect", "GET", REDIRECT_HANDLER));
        testRouter.addRoute(new Route("/logs", "GET", FILE_HANDLER));

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

        assertEquals(DIRECTORY_HANDLER.getClass(), handler.getClass());
    }

    @Test
    public void postToCustomRouteReturnsPostPutHandler() {
        handler = determineHandler("POST", "/method_options");

        assertEquals(POST_PUT_HANDLER.getClass(), handler.getClass());
    }

    @Test
    public void putToCustomDefinedRouteReturnsPostPutHandler() {
        handler = determineHandler("PUT", "/method_options");

        assertEquals(POST_PUT_HANDLER.getClass(), handler.getClass());
    }

    @Test
    public void deleteToCustomDefinedRouteReturnsDeleteHandler() {
        handler = determineHandler("DELETE", "/form");

        assertEquals(DELETE_HANDLER.getClass(), handler.getClass());
    }

    @Test
    public void optionsRequestOnlyWorksWhenCustomizedOnARoute() {
        handler = determineHandler("OPTIONS", "/method_options");

        assertEquals(OPTIONS_HANDLER.getClass(), handler.getClass());
    }

    @Test
    public void determineHandler_GivenParametersURI_ReturnsParametersHandler() {
        handler = determineHandler("GET", "/parameters?variable1=%20%3C%2C%20&variable2=stuff");

        assertEquals(PARAMETERS_HANDLER.getClass(), handler.getClass());
    }

    @Test
    public void getRedirectReturnsRedirectHandler() {
        handler = determineHandler("GET", "/redirect");

        assertEquals(REDIRECT_HANDLER.getClass(), handler.getClass());
    }

    @Test
    public void unallowedMethodReturnsNotAllowedHandler() {
        handler = determineHandler("GET", "/method_options");

        assertEquals(METHOD_NOT_ALLOWED_HANDLER.getClass(), handler.getClass());
    }

    @Test
    public void protectedRouteReturnsUnavailableHandlerIfRequestIsUnauthorized() {
        handler = determineHandler("GET", "/logs");

        assertEquals(UNAUTHORIZED_HANDLER.getClass(), handler.getClass());
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

        assertEquals(FILE_HANDLER.getClass(), handler.getClass());
    }
}

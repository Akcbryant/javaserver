package javaserver;

import java.io.File;

import javaserver.handlers.DirectoryHandler;
import javaserver.handlers.FileHandler;
import javaserver.handlers.Handler;
import javaserver.handlers.MethodNotAllowedHandler;
import javaserver.handlers.NotFoundHandler;
import javaserver.handlers.Response;
import javaserver.handlers.UnauthorizedHandler;

public class RequestHandler {

    private Request request;
    private Response response;
    private Router router;
    private Authenticator authenticator;

    public Response handleRequest(Request request) {
        Handler handler = determineHandler(request);
        return handler.handleRequest(request);
    }

    public RequestHandler(Router router, Authenticator authenticator) {
        this.router = router;
        this.authenticator = authenticator;
    }

    public Handler determineHandler(Request request) {
        String uri = removeParameters(request.getUri());
        String method = request.getMethod();

        if (authenticator.isRouteProtected(new Route(uri, method))) {
            if (!authenticator.isRequestAuthenticated(request)) {
                return new UnauthorizedHandler(router.getRootPath());
            }
        }

        if (router.hasRoute(uri)) {
            Handler handler = router.getHandler(uri, method);
            if (handler != null) {
                return handler;
            } else {
                return new MethodNotAllowedHandler();
            }
        }

        String fileUri = router.getRootPath() + uri;
        File file = getFile(fileUri);

        if (file.isFile()) return new FileHandler(fileUri);
        if (file.isDirectory()) return new DirectoryHandler(fileUri);
        return new NotFoundHandler();
    }

    private String removeParameters(String uri) {
        return uri.split("\\?")[0];
    }

    public File getFile(String path) {
        return new File(path);
    }
}

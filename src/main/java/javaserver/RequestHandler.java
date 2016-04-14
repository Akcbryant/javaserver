package javaserver;

import javaserver.handlers.DirectoryHandler;
import javaserver.handlers.FileHandler;
import javaserver.handlers.FileHandlerDecider;
import javaserver.handlers.Handler;
import javaserver.handlers.MethodNotAllowedHandler;
import javaserver.handlers.NotFoundHandler;
import javaserver.handlers.Response;
import javaserver.handlers.UnauthorizedHandler;
import javaserver.utility.FileUtility;
import javaserver.utility.ResourceUtility;

import java.io.File;

public class RequestHandler {

    private Request request;
    private Response response;
    private Router router;
    private Authenticator authenticator;

    private static final FileUtility fileUtility = new FileUtility();

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
        Route route = new Route(uri, method);

        if (authenticator.isRouteProtected(route) &&
            !authenticator.isRequestAuthenticated(request)) {
                return new UnauthorizedHandler(router.getRootPath(), fileUtility);
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

        if (isFile(file)) {
            return FileHandlerDecider.decideHandler(request, fileUri, fileUtility);
        }

        if (isDirectory(file)) { return new DirectoryHandler(fileUri); }

        return new NotFoundHandler();
    }

    private String removeParameters(String uri) {
        return uri.split("\\?")[0];
    }

    public File getFile(String path) {
        return new File(path);
    }

    protected boolean isFile(File file) {
        return file.isFile();
    }

    protected boolean isDirectory(File file) {
        return file.isDirectory();
    }
}

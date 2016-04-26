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
    private Router customRouter;
    private Authenticator authenticator;

    private static final FileUtility fileUtility = new FileUtility();

    public RequestHandler(Router customRouter, Authenticator authenticator) {
        this.customRouter = customRouter;
        this.authenticator = authenticator;
    }

    public Response handleRequest(Request request) {
        Handler handler = determineHandler(request);
        return handler.handleRequest(request);
    }

    public Handler determineHandler(Request request) {
        String uri = removeParameters(request.getUri());
        String method = request.getMethod();
        Route route = new Route(method, uri);

        if (authenticator.isRouteProtected(route) &&
            !authenticator.isRequestAuthenticated(request)) {
                return new UnauthorizedHandler(customRouter.getRootPath(), fileUtility);
        }

        if (customRouter.hasUri(route)) {
            Handler handler = customRouter.getHandler(route);
            if (handler != null) {
                return handler;
            } else {
                return new MethodNotAllowedHandler();
            }
        }

        String fileUri = customRouter.getRootPath() + uri;
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

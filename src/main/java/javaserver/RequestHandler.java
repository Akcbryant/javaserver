package javaserver;

import java.io.File;

import javaserver.handlers.DirectoryHandler;
import javaserver.handlers.FileHandler;
import javaserver.handlers.Handler;
import javaserver.handlers.MethodNotAllowedHandler;
import javaserver.handlers.NotFoundHandler;
import javaserver.handlers.Response;

public class RequestHandler {

    private Request request;
    private Response response;
    private Router router;

    public Response handleRequest(Request request) {
        Handler handler = determineHandler(request);
        return handler.handleRequest(request);
    }

    public RequestHandler(Router router) {
        this.router = router;
    }

    public Handler determineHandler(Request request) {
        String uri = removeParameters(request.getUri());
        String method = request.getMethod();

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

        if (file.isFile()) return new FileHandler();
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

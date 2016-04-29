package javaserver;

import javaserver.handlers.DeleteHandler;
import javaserver.handlers.FileHandler;
import javaserver.handlers.FormHandler;
import javaserver.handlers.HeadHandler;
import javaserver.handlers.OptionsHandler;
import javaserver.handlers.ParametersHandler;
import javaserver.handlers.PatchHandler;
import javaserver.handlers.PostPutHandler;
import javaserver.handlers.RedirectHandler;
import javaserver.helpers.ArgumentParser;
import javaserver.utility.FileUtility;

public class App {

    private static Server server;

    App(Server server) {
        this.server = server;
    }

    public static void main(String[] args) {
        String directory = ArgumentParser.getDirectory(args);
        int port = Integer.parseInt(ArgumentParser.getPort(args));

        Router cobSpecRouter = makeCobSpecRouter(directory);
        Authenticator cobSpecAuth = makeCobSpecAuthenticator();

        if (server == null) {
            server = new Server(cobSpecRouter, cobSpecAuth, port);
        }

        server.turnOn();
    }

    private static Authenticator makeCobSpecAuthenticator() {
        Authenticator authenticator = new Authenticator();

        authenticator.addAuthenticatedUser("admin:hunter2");

        authenticator.addToProtectedRoutes(new Route("GET", "/logs"));
        authenticator.addToProtectedRoutes(new Route("GET", "/log"));
        authenticator.addToProtectedRoutes(new Route("PUT", "/these"));
        authenticator.addToProtectedRoutes(new Route("HEAD", "/requests"));

        return authenticator;
    }

    private static Router makeCobSpecRouter(String directory) {
        Router router = new Router(directory);
        FileUtility fileUtility = new FileUtility();

        router.addRoute(new Route("GET", "/form", new FormHandler(directory + "/form", fileUtility)));
        router.addRoute(new Route("POST", "/form", new PostPutHandler(directory + "/form", fileUtility)));
        router.addRoute(new Route("PUT", "/form", new PostPutHandler(directory + "/form", fileUtility)));
        router.addRoute(new Route("DELETE", "/form", new DeleteHandler(directory + "/form", fileUtility)));
        router.addRoute(new Route("GET", "/redirect", new RedirectHandler("http://localhost:5000/")));
        router.addRoute(new Route("GET", "/method_options", new FileHandler(directory + "/method_options", fileUtility)));
        router.addRoute(new Route("HEAD", "/method_options", new HeadHandler()));
        router.addRoute(new Route("POST", "/method_options", new PostPutHandler(directory + "/method_options", fileUtility)));
        router.addRoute(new Route("PUT", "/method_options", new PostPutHandler(directory + "/method_options", fileUtility)));
        router.addRoute(new Route("OPTIONS", "/method_options", new OptionsHandler(router.availableMethods("/method_options"))));
        router.addRoute(new Route("GET", "/parameters",  new ParametersHandler()));
        router.addRoute(new Route("GET", "/logs", new FileHandler(directory + "/logs", fileUtility)));
        return router;
    }
}

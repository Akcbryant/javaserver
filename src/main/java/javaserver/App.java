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

    private static String directory = System.getProperty("user.dir");
    private static int port = 5000;

    public int getPort() {
        return port;
    }

    public String getDirectory() {
        return directory;
    }

    public static void main(String[] args) {
        String directory = ArgumentParser.getDirectory(args);
        int port = ArgumentParser.getPort(args);

        Router cobSpecRouter = makeCobSpecRouter(directory);
        Authenticator cobSpecAuth = makeCobSpecAuthenticator();

        Server server = new Server(cobSpecRouter, cobSpecAuth, port);
        server.turnOn();
    }

    private static Authenticator makeCobSpecAuthenticator() {
        Authenticator authenticator = new Authenticator();

        authenticator.addAuthenticatedUser("admin:hunter2");

        authenticator.addToProtectedRoutes(new Route("/logs", "GET"));
        authenticator.addToProtectedRoutes(new Route("/log", "GET"));
        authenticator.addToProtectedRoutes(new Route("/these", "PUT"));
        authenticator.addToProtectedRoutes(new Route("/requests", "HEAD"));

        return authenticator;
    }

    private static Router makeCobSpecRouter(String directory) {
        Router router = new Router(directory);
        FileUtility fileUtility = new FileUtility();

        router.addRoute(new Route("/form", "GET", new FormHandler(directory + "/form", fileUtility)));
        router.addRoute(new Route("/form", "POST", new PostPutHandler(directory + "/form", fileUtility)));
        router.addRoute(new Route("/form", "PUT", new PostPutHandler(directory + "/form", fileUtility)));
        router.addRoute(new Route("/form", "DELETE", new DeleteHandler(directory + "/form", fileUtility)));
        router.addRoute(new Route("/redirect", "GET", new RedirectHandler("http://localhost:5000/")));
        router.addRoute(new Route("/method_options", "GET", new FileHandler(directory + "/method_options", fileUtility)));
        router.addRoute(new Route("/method_options", "HEAD", new HeadHandler()));
        router.addRoute(new Route("/method_options", "POST", new PostPutHandler(directory + "/method_options", fileUtility)));
        router.addRoute(new Route("/method_options", "PUT", new PostPutHandler(directory + "/method_options", fileUtility)));
        router.addRoute(new Route("/method_options", "OPTIONS", new OptionsHandler(router.availableMethods("/method_options"))));
        router.addRoute(new Route("/parameters", "GET", new ParametersHandler()));
        router.addRoute(new Route("/patch-content.txt", "GET", new FileHandler(directory + "/patch-content.txt", fileUtility)));
        router.addRoute(new Route("/patch-content.txt", "PATCH", new PatchHandler(directory + "/patch-content.txt", fileUtility)));
        router.addRoute(new Route("/logs", "GET", new FileHandler(directory + "/logs", fileUtility)));
        return router;
    }
}

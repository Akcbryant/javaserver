package javaserver;

import javaserver.handlers.DeleteHandler;
import javaserver.handlers.FileHandler;
import javaserver.handlers.HeadHandler;
import javaserver.handlers.OptionsHandler;
import javaserver.handlers.ParametersHandler;
import javaserver.handlers.PatchHandler;
import javaserver.handlers.PostPutHandler;
import javaserver.handlers.RedirectHandler;

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
        getArgs(args);
        Router cobSpecRouter = makeCobSpecRouter(directory);
        Server server = new Server(cobSpecRouter, port);
        server.turnOn();
    }

    public static void getArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) port = Integer.parseInt(args[i + 1]);
            else if (args[i].equals("-d")) directory = args[i + 1];
        }
    }

    public static Router makeCobSpecRouter(String directory) {
        Router router = new Router(directory);
        router.addRoute(new Route("/form", "GET", new FileHandler(directory + "/form")));
        router.addRoute(new Route("/form", "POST", new PostPutHandler(directory + "/form")));
        router.addRoute(new Route("/form", "PUT", new PostPutHandler(directory + "/form")));
        router.addRoute(new Route("/form", "DELETE", new DeleteHandler(directory + "/form")));
        router.addRoute(new Route("/redirect", "GET", new RedirectHandler("http://localhost:5000/")));
        router.addRoute(new Route("/method_options", "GET", new FileHandler(directory + "/method_options")));
        router.addRoute(new Route("/method_options", "HEAD", new HeadHandler()));
        router.addRoute(new Route("/method_options", "POST", new PostPutHandler(directory + "/method_options")));
        router.addRoute(new Route("/method_options", "PUT", new PostPutHandler(directory + "/method_options")));
        router.addRoute(new Route("/method_options", "OPTIONS", new OptionsHandler(router.availableMethods("/method_options"))));
        router.addRoute(new Route("/parameters", "GET", new ParametersHandler()));
        router.addRoute(new Route("/patch-content.txt", "GET", new FileHandler(directory + "/patch-content.txt")));
        router.addRoute(new Route("/patch-content.txt", "PATCH", new PatchHandler(directory + "/patch-content.txt")));
        return router;
    }
}

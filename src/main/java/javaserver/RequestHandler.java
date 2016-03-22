package javaserver;

import java.util.List;
import java.util.Arrays;

public class RequestHandler {

    private Request request;
    private Response response;
    private Router router = new Router();
    private String method;

    public Response handleRequest(Request request) {
        Handler handler = determineHandler(request);

        return handler.handleRequest(request);
    }

    public Handler determineHandler(Request request) {
        String method = request.getMethod();
        switch (method) {
          case "GET":
              return new GetHandler();
          case "POST":
          case "PUT" :
              return new PostPutHandler();
          case "DELETE":
              return new DeleteHandler();
          case "OPTIONS":
              return new OptionsHandler(router);
          default:
              return new ErrorHandler();
        }
    }
}

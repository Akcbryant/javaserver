package javaserver;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.List;

public abstract class Handler {

    public Response response = new ResponseBuilder().buildResponse();

    static final List allowedPaths = Arrays.asList("/", "/form");
    static final Path path = Paths.get("./test");

    public Response handleRequest(Request request) {
        return response;
    }
}

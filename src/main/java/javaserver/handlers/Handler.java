package javaserver;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.List;

public abstract class Handler {

    Response response = new ResponseBuilder().buildResponse(Status.Ok);

    static final Path path = Paths.get("./test");

    public Response handleRequest(Request request) {
        return response;
    }
}

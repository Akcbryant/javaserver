package javaserver.handlers;

import java.util.Arrays;
import java.util.List;

import javaserver.Request;

public interface Handler {

    public Response handleRequest(Request request);

}

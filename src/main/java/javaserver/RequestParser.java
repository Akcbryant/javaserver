package javaserver;

import java.io.*;
import java.util.Scanner;

public class RequestParser {

    private String method;
    private String path;
    private String version;

    public Request parseRequest(InputStream input) {
        parseFirstLine(input);
        Request request = new Request(method, path, version);
        return request;
    }

    private void parseFirstLine(InputStream input) {
        Scanner scanner = new Scanner(input).useDelimiter("\\s");
        try {
            method = scanner.next();
            path = scanner.next();
            version = scanner.next();
        } catch (Exception e) {
            System.out.println("There is nothing left to scan" + e.toString());
        }
    }
}

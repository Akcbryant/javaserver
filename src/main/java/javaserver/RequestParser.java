package javaserver;

import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class RequestParser {

    String method;
    String path;
    String version;
    HashMap<String, String> headers = new HashMap<String, String>();
    String body;

    private String inputString;

    RequestParser(InputStream input) {
        try {
            inputString = convertInputStreamToString(input);
            Scanner scanner = new Scanner(inputString).useDelimiter("\r\n");
            parseFirstLine(scanner.next());
            parseHeaders(scanner.next());
            body = scanner.next();
        } catch (Exception e) {
            method = "";
            path = "";
            version = "";
            headers = new HashMap<String, String>();
            body = "";
        }
    }

    private String convertInputStreamToString(InputStream input) throws Exception {
        return new Scanner(input).useDelimiter("\\z").next();
    }

    private void parseFirstLine(String firstLineString) throws Exception {
        Scanner scanner = new Scanner(firstLineString).useDelimiter("\\s");
        method = scanner.next();
        path = scanner.next();
        version = scanner.next();
    }

    private void parseHeaders(String headersString) throws Exception {
        Scanner scanner = new Scanner(headersString).useDelimiter("\n");

        while (scanner.hasNext()) {
            String[] nextString = scanner.next().split(":");
            this.headers.put(nextString[0], nextString[1]);
        }
    }
}

package javaserver;

import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class RequestParser {

    private String inputString;
    private Request request = new Request();

    public Request parseRequest(InputStream input) {
        inputString = convertInputStreamToString(input);
        parseInput(inputString);
        return request;
    }

    private void parseInput(String input) {
        try {
            Scanner scanner = new Scanner(inputString).useDelimiter("\r\n");
            parseFirstLine(scanner.next());
            parseHeaders(scanner.next());
            request.setBody(scanner.next());
        } catch (Exception e) {
            System.out.println("Initial input is malformed.");
        }
    }

    private String convertInputStreamToString(InputStream input) {
        try {
            return new Scanner(input).useDelimiter("\\z").next();
        } catch (Exception e) {
            System.out.println("Input is non existent.");
        }
        return "";
    }

    private void parseFirstLine(String firstLineString) {
        try {
            Scanner scanner = new Scanner(firstLineString).useDelimiter("\\s");

            request.setMethod(scanner.next());
            request.setPath(scanner.next());
            request.setVersion(scanner.next());
       } catch (Exception e) {
            System.out.println("The first line of the request is not properly formed.");
       }
    }

    private void parseHeaders(String headersString) {
        try {
            Scanner scanner = new Scanner(headersString).useDelimiter("\n");
            HashMap<String, String> headers = new HashMap<String, String>();

            while (scanner.hasNext()) {
                String[] nextString = scanner.next().split(":");
                headers.put(nextString[0], nextString[1]);
            }

            request.setHeaders(headers);
        } catch (Exception e) {
            System.out.println("Error reading headers.");
        }
    }
}

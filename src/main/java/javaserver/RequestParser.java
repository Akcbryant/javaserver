package javaserver;

import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class RequestParser {

    String method = "";
    String path = "";
    String version = "";
    HashMap<String, String> headers = new HashMap<String, String>();
    String body = "";

    private String inputString;

    RequestParser(InputStream input) {
        inputString = convertInputStreamToString(input);
        parseInput(inputString);
    }

    private void parseInput(String input) {
        try {
            Scanner scanner = new Scanner(inputString).useDelimiter("\r\n");
            parseFirstLine(scanner.next());
            parseHeaders(scanner.next());
            body = scanner.next();
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

            method = scanner.next();
            path = scanner.next();
            version = scanner.next();
       } catch (Exception e) {
            System.out.println("The first line of the request is not properly formed.");
       }
    }

    private void parseHeaders(String headersString) {
        try {
            Scanner scanner = new Scanner(headersString).useDelimiter("\n");

            while (scanner.hasNext()) {
                String[] nextString = scanner.next().split(":");
                this.headers.put(nextString[0], nextString[1]);
            }
        } catch (Exception e) {
            System.out.println("Error reading headers.");
        }
    }
}

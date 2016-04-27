package javaserver;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.HashMap;

import javaserver.helpers.ParameterDecoder;

public class RequestParser {

    public static Request parseRequest(InputStream input) {
        String inputString = convertInputStreamToString(input);
        Request request = parseInput(inputString);
        return request;
    }

    private static String convertInputStreamToString(InputStream input) {
        try {
            return new Scanner(input).useDelimiter("\\z").next();
        } catch (NoSuchElementException|IllegalStateException e) {
            return "";
        }
    }

    private static Request parseInput(String input) {
        try {
            String method = parseFirstLine(input, 0);

            String completeUri = parseFirstLine(input, 1);
            String uri = ParameterDecoder.decodeUri(completeUri);
            String parameters = ParameterDecoder.decodeParameters(completeUri);

            String version = parseFirstLine(input, 2);

            HashMap<String, String> headers = parseHeaders(input);

            String body = getBody(input);

            return new Request(method, uri, parameters, version, headers, body);
        } catch (NoSuchElementException|IllegalStateException e) {
            return new Request();
        }
    }

    private static String parseFirstLine(String input, int part) throws NoSuchElementException, IllegalStateException {
        Scanner scanner = createScanner(input, "\r\n");
        String firstLine = scanner.next();
        scanner = createScanner(firstLine, "\\s");

        for (int i = 0; i < part; i++) {
            scanner.next();
        }
        return scanner.next();
    }

    private static HashMap<String, String> parseHeaders(String input) {
        try {
            Scanner scanner = createScanner(input, "\r\n");
            scanner.next();

            HashMap<String, String> headers = new HashMap<String, String>();

            while (scanner.hasNext()) {
                String[] nextString = scanner.next().split(": ");
                if (nextString.length > 1) {
                    headers.put(nextString[0], nextString[1]);
                }
            }

            return headers;
        } catch (NoSuchElementException|IllegalStateException e) {
            return new HashMap<String, String>();
        }
    }

    private static String getBody(String input) {
        try {
            Scanner scanner = createScanner(input, "\r\n\r\n");
            scanner.next();
            return scanner.next();
        } catch (NoSuchElementException|IllegalStateException e) {
            return "";
        }
    }

    private static Scanner createScanner(String input, String delimiter) {
        return new Scanner(input).useDelimiter(delimiter);
    }
}

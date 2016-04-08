package javaserver;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.HashMap;

public class RequestParser {

    private static final String HTTP_NEW_LINE = "\r\n";
    private static final String ALL = "\\z";
    private static final String HEADER_SEPARATOR = ": ";
    private static final String SPACE_SEPARATOR = "\\s";

    private static final int KEY = 0;
    private static final int VALUE = 1;

    private static final int METHOD_PART = 0;
    private static final int URI_PART = 1;
    private static final int VERSION_PART = 2;


    public Request parseRequest(InputStream input) {
        String inputString = convertInputStreamToString(input);
        Request request = parseInput(inputString);
        return request;
    }

    private String convertInputStreamToString(InputStream input) {
        try {
            return new Scanner(input).useDelimiter(ALL).next();
        } catch (NoSuchElementException|IllegalStateException e) {
            return "";
        }
    }

    private Request parseInput(String input) {
        try {
            String method = parseFirstLine(input, METHOD_PART);
            String uri = parseFirstLine(input, URI_PART);
            String version = parseFirstLine(input, VERSION_PART);

            HashMap<String, String> headers = parseHeaders(input);

            String body = getBody(input);

            return new Request(method, uri, version, headers, body);
        } catch (NoSuchElementException|IllegalStateException e) {
            return new Request();
        }
    }

    private String parseFirstLine(String input, int part) throws NoSuchElementException, IllegalStateException {
        Scanner scanner = createScanner(input, HTTP_NEW_LINE);
        String firstLine = scanner.next();
        scanner = createScanner(firstLine, SPACE_SEPARATOR);

        for (int i = 0; i < part; i++) {
            scanner.next();
        }
        return scanner.next();
    }

    private HashMap<String, String> parseHeaders(String input) {
        try {
            Scanner scanner = createScanner(input, HTTP_NEW_LINE);
            scanner.next();

            HashMap<String, String> headers = new HashMap<String, String>();

            while (scanner.hasNext()) {
                String[] nextString = scanner.next().split(HEADER_SEPARATOR);
                if (nextString.length > 1) {
                    headers.put(nextString[KEY], nextString[VALUE]);
                }
            }

            return headers;
        } catch (NoSuchElementException|IllegalStateException e) {
            return new HashMap<String, String>();
        }
    }

    private String getBody(String input) {
        try {
            Scanner scanner = createScanner(input, HTTP_NEW_LINE + HTTP_NEW_LINE);
            scanner.next();
            return scanner.next();
        } catch (NoSuchElementException|IllegalStateException e) {
            return "";
        }

    }

    private Scanner createScanner(String input, String delimiter) {
        return new Scanner(input).useDelimiter(delimiter);
    }
}

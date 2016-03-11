package javaserver;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.HashMap;

public class RequestParser {

    private String inputString;
    private Request request = new Request();

    private String method = "";
    private String path = "";
    private String version = "";
    private HashMap<String, String> headers = new HashMap<String, String>();
    private String body = "";

    public Request parseRequest(InputStream input) {
        inputString = convertInputStreamToString(input);
        parseInput(inputString);
        Request request = new Request(method, path, version, headers, body);
        return request;
    }

    private String convertInputStreamToString(InputStream input) {
        try {
            return new Scanner(input).useDelimiter("\\z").next();
        } catch (NoSuchElementException|IllegalStateException e) {
            System.out.println("Input is non existent.");
        }
        return "";
    }

    private void parseInput(String input) {
        try {
            Scanner scanner = new Scanner(inputString).useDelimiter("\r\n");
            parseFirstLine(scanner.next());
            headers = parseHeaders(scanner);

            scanner = new Scanner(inputString).useDelimiter("\r\n\r\n");
            scanner.next();
            body = scanner.next();
        } catch (NoSuchElementException|IllegalStateException e) {
            System.out.println("There was an error parsing your request.");
        }
    }

    private void parseFirstLine(String firstLineString) throws NoSuchElementException, IllegalStateException {
        Scanner scanner = new Scanner(firstLineString).useDelimiter("\\s");

        method = scanner.next();
        path = scanner.next();
        version = scanner.next();
    }

    private HashMap<String, String> parseHeaders(Scanner scanner) throws NoSuchElementException, IllegalStateException {

        HashMap<String, String> headers = new HashMap<String, String>();

        while (scanner.hasNext()) {
            String[] nextString = scanner.next().split(":");
            if (nextString.length > 1) {
                System.out.println(nextString[0]);
                headers.put(nextString[0], nextString[1]);
            }
        }

        return headers;
    }
}

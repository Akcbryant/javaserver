package javaserver;

import java.io.*;
import java.util.Scanner;

public class RequestParser {

    private String method;
    private String path;
    private String version;

    private Scanner scanner;

    public Request parseRequest(InputStream input) {
        scanner = new Scanner(input).useDelimiter("\\s");
        parseFirstLine();
        Request request = new Request(method, path, version);
        return request;
    }

    private void parseFirstLine() {
      try {
          method = scanner.next();
          path = scanner.next();
          version = scanner.next();
      } catch (Exception e) {
          System.out.println("There is nothing left to scan" + e.toString());
      }
      scanner.close();
    }

}

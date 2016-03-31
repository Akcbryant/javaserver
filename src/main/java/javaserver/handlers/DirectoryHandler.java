package javaserver.handlers;

import java.io.File;
import javaserver.Request;

public class DirectoryHandler implements Handler {

    protected static final String HTMLFORMAT = "<a href=\"%s\">%s</a></br>\r\n";
    private String baseUri;
    private Request request;

    public DirectoryHandler(String baseUri) {
        this.baseUri = baseUri;
    }

    public Response handleRequest(Request request) {
        this.request = request;
        Response response = new Response();

        String body = createDirectoryListBody(baseUri);
        response.setBody(body);
        response.setStatus(Status.Ok);

        return response;
    }

    private String createDirectoryListBody(String uri) {
        String[] files = getFilesList(uri);

        String htmlDirectoryList = "";
        if (files != null) {
            for (String file : files) {
              String newUri = "/" + file;
              if (!request.getUri().equals("/")) {
                  newUri = request.getUri() + "/" + file;
              }
              htmlDirectoryList += String.format(HTMLFORMAT, newUri, file);
            }
        }

        return htmlDirectoryList;
    }

    public String[] getFilesList(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.list();
    }
}

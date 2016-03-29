package javaserver.handlers;

import java.io.File;
import javaserver.Request;
import javaserver.Router;

public class DirectoryHandler implements Handler {

    private String baseUri;

    public DirectoryHandler(String baseUri) {
        this.baseUri = baseUri;
    }

    public Response handleRequest(Request request) {
        Response response = new Response();

        String body = createDirectoryListBody(baseUri + request.getUri());
        response.setBody(body);
        response.setStatus(Status.Ok);

        return response;
    }

    private String createDirectoryListBody(String uri) {
        String[] files = getFilesList(uri);

        String htmlDirectoryList = "";
        if (files != null) {
            for (String file : files) {
              htmlDirectoryList += "<a href=\"" + file + "\">" + file + "</a>\r\n";
            }
        }

        return htmlDirectoryList;
    }

    public String[] getFilesList(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.list();
    }
}

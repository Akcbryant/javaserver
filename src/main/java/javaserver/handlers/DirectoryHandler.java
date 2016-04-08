package javaserver.handlers;

import java.io.File;
import java.io.FilenameFilter;

import javaserver.Request;

public class DirectoryHandler implements Handler {

    protected static final String HTMLFORMAT = "<a href=\"%s\">%s</a></br>\r\n";
    private static final String SLASH = "/";
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
        String[] fileNames = getFilesList(uri);
        String requestUri = request.getUri();

        String htmlDirectoryList = "";
        if (fileNames != null) {
            for (String fileName : fileNames) {
                String newUri = SLASH + fileName;
                if (!isRoot(requestUri)) {
                    newUri = requestUri +  SLASH + fileName;
                }
                htmlDirectoryList += String.format(HTMLFORMAT, newUri, fileName);
            }
        }
        return htmlDirectoryList;
    }

    private boolean isRoot(String uri) {
        return uri.equals(SLASH);
    }

    private boolean isHidden(String fileName) {
        return fileName.startsWith(".");
    }

    public String[] getFilesList(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.list(new HiddenFilenameFilter());
    }

    private static class HiddenFilenameFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return !name.startsWith(".");
        }
    }
}

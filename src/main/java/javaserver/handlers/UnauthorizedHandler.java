package javaserver.handlers;

import javaserver.Request;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class UnauthorizedHandler implements Handler {

    private String fileUri;
    private Response response = new Response();

    public UnauthorizedHandler(String fileUri) {
        this.fileUri = fileUri;
    }

    public Response handleRequest(Request request) {
        response.setStatus(Status.Unauthorized);

        String header = "WWW-Authenticate: Basic";
        response.setHeaders(header);

        String firstLine = request.getFirstLine();
        logString(firstLine, fileUri);

        return response;
    }

    protected void logString(String text, String fileUri) {
        File file = new File(fileUri + "/logs");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}

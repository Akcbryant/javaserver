package javaserver.handlers;

import javaserver.Request;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class PatchHandler implements Handler {

    private String fileUri = "";
    private Response response = new Response();

    public PatchHandler(String fileUri) {
        this.fileUri = fileUri;
    }

    public Response handleRequest(Request request) {
        String etag = request.getHeaders().get("If-Match");

        if (matchesEtag(etag)) {
            String content = request.getBody();
            writeContent(content);
        }

        response.setStatus(Status.NoContent);
        return response;
    }

    protected boolean matchesEtag(String etag) {
        byte[] fileContents = getFileContents(fileUri);
        String fileSHA = SHA1Encoder.generateSHA(fileContents);
        return fileSHA.equals(etag);
    }

    private byte[] getFileContents(String fileUri) {
        File file = new File(fileUri);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            return new byte[0];
        }
    }

    private void writeContent(String content) {
        try (PrintWriter writer = new PrintWriter(fileUri, "UTF-8")) {
            writer.print(content);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}

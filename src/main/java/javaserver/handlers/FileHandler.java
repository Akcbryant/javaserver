package javaserver.handlers;

import javaserver.Request;

import java.util.Arrays;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class FileHandler implements Handler {

    private String fileUri;
    private Response response = new Response();
    private int contentSize = 0;

    public FileHandler(String fileUri) {
        this.fileUri = fileUri;
    }

    public Response handleRequest(Request request) {
        response = new Response();

        String method = request.getMethod();
        if (!method.equals("GET")) {
            response.setStatus(Status.MethodNotAllowed);
            return response;
        }

        byte[] body = getFileContents(fileUri);
        if (request.getHeaders().containsKey("Range")) {
            String range = request.getHeaders().get("Range");
            int start = getFirstNumberInRange(range, body.length);
            int end = getLastNumberInRange(range, body.length);

            body = getRangeOfBytes(body, start, end);

            String headers = "";
            headers = "Content-Length: " + body.length + "\r\n";
            headers += "Content-Type: txt/plain" + "\r\n";
            response.setHeaders(headers);

            response.setStatus(Status.PartialContent);
        } else {
            String uri = request.getUri();
            String[] splitUri = uri.split("\\.");

            if (splitUri.length > 1) {
                String headers = "";
                headers = "Content-Length: " + contentSize + "\r\n";
                headers += "Content-Type: image/" + splitUri[1] + "\r\n";
                response.setHeaders(headers);
            }
        }
        response.setBody(body);
        return response;
    }

    public byte[] getFileContents(String fileUri) {
        File file = new File(fileUri);

        try {
            byte[] content = Files.readAllBytes(file.toPath());
            contentSize = content.length;
            response.setStatus(Status.Ok);
            return content;
        } catch (IOException e) {
            response.setStatus(Status.ServerError);
            return new byte[0];
        }
    }

    public int getFirstNumberInRange(String contentRange, int contentLength) {
        String range = contentRange.split("=")[1];
        String firstNumber = range.split("-")[0];

        if (range.startsWith("-")) {
            int number = Integer.parseInt(range.substring(1));
            return  Math.max(contentLength - number, 0);
        } else {
            return Integer.parseInt(firstNumber);
        }
    }

    public int getLastNumberInRange(String contentRange, int contentLength) {
        String range = contentRange.split("=")[1];

        if (range.endsWith("-") || range.startsWith("-")) {
            return contentLength;
        } else {
            int lastNumber = Integer.parseInt(range.split("-")[1]);
            return lastNumber + 1;
        }
    }

    public byte[] getRangeOfBytes(byte[] content, int start, int end) {
        return Arrays.copyOfRange(content, start, end);
    }


}

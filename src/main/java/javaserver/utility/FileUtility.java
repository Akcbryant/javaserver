package javaserver.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;

public class FileUtility implements ResourceUtility {

    public void createResource(String fileUri, byte[] data) throws IOException {
        Path filePath = getFilePath(fileUri);
        Files.write(filePath, data);
    }

    public byte[] readResource(String fileUri) throws IOException {
        Path filePath = getFilePath(fileUri);
        return Files.readAllBytes(filePath);
    }

    public void updateResource(String fileUri, byte[] data) throws IOException {
        Path filePath = getFilePath(fileUri);
        Files.write(filePath, data, StandardOpenOption.APPEND);
    }

    public void deleteResource(String fileUri) throws IOException {
        Path filePath = getFilePath(fileUri);
        Files.delete(filePath);
    }

    protected Path getFilePath(String fileUri) {
        return new File(fileUri).toPath();
    }
}

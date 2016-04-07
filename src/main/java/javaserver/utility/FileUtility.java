package javaserver.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtility implements ResourceUtility {

    public void createResource(String fileUri, byte[] data) throws IOException {
        File file = new File(fileUri);
        Files.write(file.toPath(), data);
    }

    public byte[] readResource(String fileUri) throws IOException {
        File file = new File(fileUri);
        byte[] content = Files.readAllBytes(file.toPath());
        return content;
    }

    public void updateResource(String fileUri, byte[] data) throws IOException {
        File file = new File(fileUri);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);

        String text = new String(data);
        bw.write(text);
        bw.close();
    }

    public void deleteResource(String fileUri) throws IOException {
        File file = new File(fileUri);
        Files.delete(file.toPath());
    }
}

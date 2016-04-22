package javaserver.handlers;

import javaserver.utility.ResourceUtility;
import java.io.IOException;

public class FormHandler extends FileHandler {

    public FormHandler(String fileUri, ResourceUtility resourceUtility) {
        super(fileUri, resourceUtility);
        this.fileUri = fileUri;
        this.resourceUtility = resourceUtility;
    }

    @Override
    public byte[] getFileContents(String fileUri, ResourceUtility resourceUtility) {
        try {
            byte[] content = resourceUtility.readResource(fileUri);
            response.setStatus(Status.OK);
            return content;
        } catch (IOException e) {
            response.setStatus(Status.OK);
            return new byte[0];
        }
    }
}

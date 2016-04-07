package javaserver.utility;

import java.io.IOException;

public interface ResourceUtility {

    public void createResource(String location, byte[] data) throws IOException;
    public byte[] readResource(String location) throws IOException;
    public void updateResource(String location, byte[] data) throws IOException;
    public void deleteResource(String location) throws IOException;

}

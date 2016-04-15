package javaserver.utility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtilityTest {

    private static final String TEST_URI = "/test";
    private static final FileUtility FILE_UTILITY = new FileUtility();

    @Test
    public void returnedPathHasSameString() {
        Path path = FILE_UTILITY.getFilePath("/test");

        assertEquals(TEST_URI, path.toString());
    }
}

package javaserver.helpers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ArgumentParserTest {

    private static final String TEST_PORT = "1234";
    private static final String TEST_DIRECTORY = "test";

    private String[] args;

    @Test
    public void getArgsUsesDefaultArgsWhenGivenNoParameters() {
        args = new String[] {""};

        assertEquals(ArgumentParser.DEFAULT_PORT, ArgumentParser.getPort(args));
        assertEquals(ArgumentParser.DEFAULT_DIRECTORY, ArgumentParser.getDirectory(args));
    }

    @Test
    public void getArgsSetsDirectoryAndPort() {
        args = new String[] {ArgumentParser.PORT_FLAG, TEST_PORT,
                             ArgumentParser.DIRECTORY_FLAG, TEST_DIRECTORY};

        assertEquals(TEST_PORT, ArgumentParser.getPort(args));
        assertEquals(TEST_DIRECTORY, ArgumentParser.getDirectory(args));
    }

    @Test
    public void getArgsSetsDirectoryAndPortWhenParametersAreOutOfOrder() {
        args = new String[] {ArgumentParser.DIRECTORY_FLAG, TEST_DIRECTORY, ArgumentParser.PORT_FLAG, TEST_PORT};

        assertEquals(TEST_PORT, ArgumentParser.getPort(args));
        assertEquals(TEST_DIRECTORY, ArgumentParser.getDirectory(args));
    }
}

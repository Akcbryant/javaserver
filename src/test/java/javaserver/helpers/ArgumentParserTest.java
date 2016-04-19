package javaserver.helpers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ArgumentParserTest {

    String[] args;

    @Test
    public void getArgsUsesDefaultArgsWhenGivenNoParameters() {
        args = new String[] {""};

        assertEquals(5000, ArgumentParser.getPort(args));
        assertEquals(System.getProperty("user.dir"), ArgumentParser.getDirectory(args));
    }

    @Test
    public void getArgsSetsDirectoryAndPort() {
        args = new String[] {"-p", "1234", "-d", "test"};

        assertEquals(1234, ArgumentParser.getPort(args));
        assertEquals("test", ArgumentParser.getDirectory(args));
    }

    @Test
    public void getArgsSetsDirectoryAndPortWhenParametersAreOutOfOrder() {
        args = new String[] {"-d", "test", "-p", "1234"};

        assertEquals(1234, ArgumentParser.getPort(args));
        assertEquals("test", ArgumentParser.getDirectory(args));
    }
}

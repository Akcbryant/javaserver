package javaserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {

    private App app;
    private String[] args;

    private void testAppWithArgs(String[] args) {
        app = new App();
        app.getArgs(args);
    }

    @Test
    public void getArgsUsesDefaultArgsWhenGivenNoParameters() {
        args = new String[] {""};
        testAppWithArgs(args);

        assertEquals(5000, app.getPort());
        assertEquals(System.getProperty("user.dir"), app.getDirectory());
    }

    @Test
    public void getArgsSetsDirectoryAndPort() {
        args = new String[] {"-p", "1234", "-d", "test"};
        testAppWithArgs(args);

        assertEquals(1234, app.getPort());
        assertEquals("test", app.getDirectory());
    }

    @Test
    public void getArgsSetsDirectoryAndPortWhenParametersAreOutOfOrder() {
        args = new String[] {"-d", "test", "-p", "1234"};
        testAppWithArgs(args);

        assertEquals(1234, app.getPort());
        assertEquals("test", app.getDirectory());
    }
}

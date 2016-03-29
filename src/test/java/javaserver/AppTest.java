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

    //@Test
    //public void getArgs_GivenNoArgs_DirectoryAndPortAreDefaults() {
        //args = new String[0];
        //testAppWithArgs(args);

        //assertEquals(5000, app.getPort());
        //assertEquals(System.getProperty("user.dir") + "/public/", app.getDirectory());
    //}

    @Test
    public void getArgs_GivenBothArgs_SetsDirectoryAndPort() {
        args = new String[] {"-p", "1234", "-d", "test"};
        testAppWithArgs(args);

        assertEquals(1234, app.getPort());
        assertEquals("test", app.getDirectory());
    }

    @Test
    public void getArgs_GivenOutOfOrderArgs_SetsDirectoryAndPort() {
        args = new String[] {"-d", "test", "-p", "1234"};
        testAppWithArgs(args);

        assertEquals(1234, app.getPort());
        assertEquals("test", app.getDirectory());
    }
}

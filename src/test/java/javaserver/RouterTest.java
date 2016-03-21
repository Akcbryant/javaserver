package javaserver;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.HashMap;

public class RouterTest {

    private HashMap<String, String> headers = new HashMap<String, String>();
    private Router router = new Router();

    @Test
    public void pathIsAllowed() {
        assertTrue(router.pathIsAllowed("/"));
    }
}

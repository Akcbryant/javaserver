package javaserver;

import java.util.HashMap;

public class CobSpecRoutes implements Routes {

    CobSpecRoutes() {
        routes.put("GET", new String[]{"/", "/form", "/method_options", "/parameters"});
        routes.put("HEAD", new String[]{"/method_options"});
        routes.put("POST", new String[]{"/form", "/method_options"});
        routes.put("OPTIONS", new String[]{"/", "/form", "/method_options"});
        routes.put("PUT", new String[]{"/form", "/method_options"});
        routes.put("DELETE", new String[]{"/form"});

        redirects.put("/redirect", "http://localhost:5000/");
    }
}

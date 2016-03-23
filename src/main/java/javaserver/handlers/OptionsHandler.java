package javaserver;

public class OptionsHandler extends Handler {

    Router router;

    OptionsHandler() {
        router = new Router();
    }

    OptionsHandler(Router router) {
        this.router = router;
    }

    public Response handleRequest(Request request) {
        Response response = new ResponseBuilder().buildResponse();

        String headers = "Allow:" + router.availableMethods(request.getUri());
        response.setHeaders(headers);

        return response;
    }
}

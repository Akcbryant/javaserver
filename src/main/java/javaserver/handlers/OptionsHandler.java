package javaserver;

public class OptionsHandler extends Handler {

    Router router;

    OptionsHandler() {
        router = new Router(new CobSpecRoutes());
    }

    OptionsHandler(Router router) {
        this.router = router;
    }

    public Response handleRequest(Request request) {
        Response response = new ResponseBuilder().buildResponse(Status.Ok);

        String headers = "Allow:" + router.availableMethods(request.getUri());
        response.setHeaders(headers);

        return response;
    }
}

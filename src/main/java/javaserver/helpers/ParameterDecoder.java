package javaserver.helpers;

import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

public class ParameterDecoder {

    protected static String getParametersString(String uri) {
        String[] splitUri = uri.split("\\?");
        if (splitUri.length > 1) {
            return splitUri[1];
        }
        return "";
    }

    protected static String decodeString(String input) {
        try {
            return new URLDecoder().decode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String decodeUri(String input) {
        String uri = input.split("\\?")[0];
        return decodeString(uri);
    }

    public static String decodeParameters(String input) {
        String params = getParametersString(input);

        params = params.replace("=", " = ");
        params = params.replace("&", "\r\n");
        params = decodeString(params);

        return params;
    }
}

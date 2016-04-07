package javaserver.helpers;

import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

public class ParameterDecoder {

    public boolean hasParameters(String uri) {
        return uri.contains("?");
    }

    public String getParametersString(String uri) {
        String[] splitUri = uri.split("\\?");
        if (splitUri.length > 1) {
            return splitUri[1];
        }
        return "";
    }

    public String decodeString(String input) {
        try {
            return new URLDecoder().decode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public String decodeUri(String input) {
        String params = getParametersString(input);

        params = params.replace("=", " = ");
        params = params.replace("&", "\r\n");
        params = decodeString(params);

        return params;
    }
}

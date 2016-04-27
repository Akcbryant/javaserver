package javaserver.helpers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParameterDecoderTest {

    private ParameterDecoder decoder = new ParameterDecoder();
    private static final String NO_PARAMS = "/parameters";
    private static final String ONE_PARAM = "/parameters?test=data";
    private static final String MULTIPLE_PARAMS = "/parameters?test=data&new=york";
    private static final String ONE_ENCODED_PARAM = "/parameters?variable1=%20%3C%2C%20";
    private static final String MULTIPLE_ENCODED_PARAMS = "/parameters?variable1=%20%3C%2C%20&variable2=stuff";
    private static final String ENCODED_URI_AND_PARAM = "/test%20test?%20test%20";
    private static final String RETURN_BODY = "variable1 =  <, \r\nvariable2 = stuff";

    @Test
    public void getParamsWithNoParamsReturnsEmptyString() {
        assertEquals("", decoder.getParametersString(NO_PARAMS));
    }

    @Test
    public void getParamsWithOneOrMoreParamsReturnsParameterString() {
        assertEquals("test=data", decoder.getParametersString(ONE_PARAM));
        assertEquals("test=data&new=york", decoder.getParametersString(MULTIPLE_PARAMS));
    }

    @Test
    public void decodeUriSplitsUriAndReturnsDecodedUriString() {
        assertEquals("/test test", decoder.decodeUri(ENCODED_URI_AND_PARAM));
    }

    @Test
    public void decodingEmptyStringReturnsEmptyString() {
        assertEquals("", decoder.decodeString(""));
    }

    @Test
    public void decodingEncodedStringReturnsDecodedString() {
        assertEquals(" <, ", decoder.decodeString("%20%3C%2C%20"));
    }

    @Test
    public void decodeParametersWithNoParamsReturnsEmptyString() {
        assertEquals("", decoder.decodeParameters(NO_PARAMS));
    }

    @Test
    public void decodeParametersWithEncodedParamReturnsDecodedString() {
        assertEquals(RETURN_BODY, decoder.decodeParameters(MULTIPLE_ENCODED_PARAMS));
    }
}

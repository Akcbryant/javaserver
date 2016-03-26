package javaserver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParameterDecoderTest {

    private ParameterDecoder decoder = new ParameterDecoder();
    private static final String noParams = "/parameters";
    private static final String oneParam = "/parameters?test=data";
    private static final String multipleParams = "/parameters?test=data&new=york";
    private static final String oneEncodedParam = "/parameters?variable1=%20%3C%2C%20";
    private static final String multipleEncodedParams = "/parameters?variable1=%20%3C%2C%20&variable2=stuff";
    private static final String returnBody = "variable1 =  <, \r\nvariable2 = stuff";

    @Test
    public void checkForParametersGivenNoParameters() {
        assertFalse(decoder.hasParameters(noParams));
    }

    @Test
    public void checkForParametersGivenParameters() {
        assertTrue(decoder.hasParameters(oneParam));
    }

    @Test
    public void getParamsWithNoParamsReturnsEmptyString() {
        assertEquals("", decoder.getParametersString(noParams));
    }

    @Test
    public void getParamsWithOneOrMoreParamsReturnsParameterString() {
        assertEquals("test=data", decoder.getParametersString(oneParam));
        assertEquals("test=data&new=york", decoder.getParametersString(multipleParams));
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
    public void decodeURIWithNoParamsReturnsEmptyString() {
        assertEquals("", decoder.decodeURI(noParams));
    }

    @Test
    public void decodeURIWithEncodedParamReturnsDecodedString() {
        assertEquals(returnBody, decoder.decodeURI(multipleEncodedParams));
    }

}

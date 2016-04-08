package javaserver.helpers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SHA1EncoderTest {

    @Test
    public void encoderReturnsCorrectSHA1EncodedString() {
        assertEquals("a54d88e06612d820bc3be72877c74f257b561b19", SHA1Encoder.generateSHA("This is a test".getBytes()));
    }
}

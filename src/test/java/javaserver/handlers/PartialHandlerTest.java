package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.ResourceUtility;
import javaserver.utility.FileUtility;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class PartialHandlerTest {

    private static final int CONTENT_LENGTH = "test".getBytes().length;
    private static final byte[] CONTENT = "test".getBytes();

    private String contentRange;
    private Request request;
    private Response response;

    private static final FileUtility FILE_UTILITY = new FileUtility();
    private static final PartialHandler PARTIAL_HANDLER = new PartialHandler("", FILE_UTILITY);

    @Before
    public void setUp() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Range", "bytes=-3");

        request = new Request();

        request.setHeaders(headers);
    }


    @Test
    public void getBeginningOfContentRangeReturnsFirstNumberInRange() {
        contentRange = "bytes=8-9";

        int firstNumberInRange = PARTIAL_HANDLER.getFirstNumberInRange(contentRange, CONTENT_LENGTH);
        assertEquals(8, firstNumberInRange);
    }

    @Test
    public void returnsContentLengthMinusNumberWhenFirstNumberIsAbsent() {
        contentRange = "bytes=-9";

        int firstNumberInRange = PARTIAL_HANDLER.getFirstNumberInRange(contentRange, CONTENT_LENGTH);

        assertEquals(0, firstNumberInRange);
    }

    @Test
    public void getFirstNumberReturnsNumberWhenLastNumberIsEmpty() {
        contentRange = "bytes=9-";

        int firstNumberInRange = PARTIAL_HANDLER.getFirstNumberInRange(contentRange, CONTENT_LENGTH);

        assertEquals(9, firstNumberInRange);
    }

    @Test
    public void getEndingNumberReturnsSecondNumberInRange() {
        contentRange = "bytes=8-9";

        int lastNumberInRange = PARTIAL_HANDLER.getLastNumberInRange(contentRange, CONTENT_LENGTH);

        assertEquals(10, lastNumberInRange);
    }

    @Test
    public void returnsLengthOfContentsIfSecondNumberIsNotThere() {
        contentRange = "bytes=9-";

        int lastNumberInRange = PARTIAL_HANDLER.getLastNumberInRange(contentRange, CONTENT_LENGTH);

        assertEquals(CONTENT_LENGTH, lastNumberInRange);
    }

    @Test
    public void returnsNumberIfFirstNumberIsMissing() {
        contentRange = "bytes=-9";

        int lastNumberInRange = PARTIAL_HANDLER.getLastNumberInRange(contentRange, CONTENT_LENGTH);

        assertEquals(CONTENT_LENGTH, lastNumberInRange);
    }

    @Test
    public void returnedResponseHasAppropriateLength() {
        response = new MockPartialHandler(CONTENT).handleRequest(request);

        assertEquals(3, response.getByteBody().length);
    }

    private class MockPartialHandler extends PartialHandler {

        private byte[] fileContents;

        MockPartialHandler(byte[] fileContents) {
            super(null, null);
            this.fileContents = fileContents;
        }

        public byte[] getFileContents(String fileUri, ResourceUtility resourceUtility) {
            return fileContents;
        }
    }
}

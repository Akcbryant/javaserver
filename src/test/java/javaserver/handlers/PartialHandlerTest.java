package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.FileUtility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PartialHandlerTest {

    String contentRange;
    int contentLength = "test".getBytes().length;
    byte[] content = "test".getBytes();

    private static final FileUtility FILE_UTILITY = new FileUtility();
    private static final PartialHandler PARTIAL_HANDLER = new PartialHandler("", FILE_UTILITY);
    @Test
    public void getBeginningOfContentRangeReturnsFirstNumberInRange() {
        contentRange = "bytes=8-9";

        int firstNumberInRange = PARTIAL_HANDLER.getFirstNumberInRange(contentRange, contentLength);
        assertEquals(8, firstNumberInRange);
    }

    @Test
    public void returnsContentLengthMinusNumberWhenFirstNumberIsAbsent() {
        contentRange = "bytes=-9";

        int firstNumberInRange = PARTIAL_HANDLER.getFirstNumberInRange(contentRange, contentLength);

        assertEquals(0, firstNumberInRange);
    }

    @Test
    public void getFirstNumberReturnsNumberWhenLastNumberIsEmpty() {
        contentRange = "bytes=9-";

        int firstNumberInRange = PARTIAL_HANDLER.getFirstNumberInRange(contentRange, contentLength);

        assertEquals(9, firstNumberInRange);
    }

    @Test
    public void getEndingNumberReturnsSecondNumberInRange() {
        contentRange = "bytes=8-9";

        int lastNumberInRange = PARTIAL_HANDLER.getLastNumberInRange(contentRange, contentLength);

        assertEquals(10, lastNumberInRange);
    }

    @Test
    public void returnsLengthOfContentsIfSecondNumberIsNotThere() {
        contentRange = "bytes=9-";

        int lastNumberInRange = PARTIAL_HANDLER.getLastNumberInRange(contentRange, contentLength);

        assertEquals(contentLength, lastNumberInRange);
    }

    @Test
    public void returnsNumberIfFirstNumberIsMissing() {
        contentRange = "bytes=-9";

        int lastNumberInRange = PARTIAL_HANDLER.getLastNumberInRange(contentRange, contentLength);

        assertEquals(contentLength, lastNumberInRange);
    }

}

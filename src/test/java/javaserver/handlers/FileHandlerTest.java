package javaserver.handlers;

import javaserver.Request;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FileHandlerTest {

    private static final String SUCCESSMESSAGE = "File Reading Successful";

    Request request = new Request();
    Response response;
    String contentRange;
    int contentLength = "test".getBytes().length;
    byte[] content = "test".getBytes();

    @Test
    public void methodThatIsAnthingButGETSetsStatusToMethodNotAllowed() {
        request.setMethod("POST");

        response = new FileHandler("").handleRequest(request);

        assertEquals(Status.MethodNotAllowed, response.getStatus());
    }

    @Test
    public void setResponseBodyToSuccessfullyReadFileContents() {
        request.setMethod("GET");
        response = new MockFileHandler(true).handleRequest(request);

        assertEquals(SUCCESSMESSAGE, new String(response.getByteBody()));
    }

    @Test
    public void setResponseBodyToEmptyUponFailureToReadFileContents() {
        response = new MockFileHandler(false).handleRequest(request);

        assertEquals("", response.getBody());
    }

    @Test
    public void getBeginningOfContentRangeReturnsFirstNumberInRange() {
        contentRange = "bytes=8-9";

        int firstNumberInRange = new FileHandler("").getFirstNumberInRange(contentRange, contentLength);
        assertEquals(8, firstNumberInRange);
    }

    @Test
    public void returnsContentLengthMinusNumberWhenFirstNumberIsAbsent() {
        contentRange = "bytes=-9";

        int firstNumberInRange = new FileHandler("").getFirstNumberInRange(contentRange, contentLength);

        assertEquals(0, firstNumberInRange);
    }

    @Test
    public void getFirstNumberReturnsNumberWhenLastNumberIsEmpty() {
        contentRange = "bytes=9-";

        int firstNumberInRange = new FileHandler("").getFirstNumberInRange(contentRange, contentLength);

        assertEquals(9, firstNumberInRange);
    }

    @Test
    public void getEndingNumberReturnsSecondNumberInRange() {
        contentRange = "bytes=8-9";

        int lastNumberInRange = new FileHandler("").getLastNumberInRange(contentRange, contentLength);

        assertEquals(10, lastNumberInRange);
    }

    @Test
    public void returnsLengthOfContentsIfSecondNumberIsNotThere() {
        contentRange = "bytes=9-";

        int lastNumberInRange = new FileHandler("").getLastNumberInRange(contentRange, contentLength);

        assertEquals(contentLength, lastNumberInRange);
    }

    @Test
    public void returnsNumberIfFirstNumberIsMissing() {
        contentRange = "bytes=-9";

        int lastNumberInRange = new FileHandler("").getLastNumberInRange(contentRange, contentLength);

        assertEquals(contentLength, lastNumberInRange);
    }

    @Test
    public void getRangeOfBytesReturnsAppropriateRangeOfBytes() {
        byte[] byteRange = new FileHandler("").getRangeOfBytes(content, 0, 4);

        assertEquals("test", new String(byteRange));
    }

    private class MockFileHandler extends FileHandler {

        private boolean fileReadSuccessful;

        MockFileHandler(boolean fileReadSuccessful) {
            super("");
            this.fileReadSuccessful = fileReadSuccessful;
        }

        @Override
        public byte[] getFileContents(String fileUri) {
            if (fileReadSuccessful) {
                return SUCCESSMESSAGE.getBytes();
            }
            return "".getBytes();
        }
    }
}

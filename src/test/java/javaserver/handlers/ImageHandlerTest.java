package javaserver.handlers;

import javaserver.Request;
import javaserver.utility.FileUtility;
import javaserver.utility.ResourceUtility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ImageHandlerTest {

    ImageHandler imageHandler = new ImageHandler("", new FileUtility());

    @Test
    public void getImageTypeReturnsAnythingAfterPeriod() {
        assertEquals("png", imageHandler.getImageType("testing.png"));
    }

    @Test
    public void fileReadIsSuccessfulHasContentRangeAndTypeHeaders() {
        MockImageHandler mockImageHandler = new MockImageHandler("testing.png", new FileUtility());

        Response response = mockImageHandler.handleRequest(new Request());

        assertEquals(mockImageHandler.makeImageHeaders(4,"png"), response.getHeaders());
    }

    private class MockImageHandler extends ImageHandler {

        MockImageHandler(String fileUri, ResourceUtility resourceUtility) {
            super(fileUri, resourceUtility);
        }

        @Override
        public byte[] getFileContents(String fileUri, ResourceUtility resourceUtility) {
            return "test".getBytes();
        }
    }
}

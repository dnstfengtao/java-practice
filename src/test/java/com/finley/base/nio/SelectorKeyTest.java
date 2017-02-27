package com.finley.base.nio;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2/27/17.
 */
public class SelectorKeyTest {

    @Test
    public void testInterestOptions() throws IOException {
        final SelectionKey mockSelectionKey = new SelectionKey() {
            @Override
            public SelectableChannel channel() {
                return null;
            }

            @Override
            public Selector selector() {
                return null;
            }

            @Override
            public boolean isValid() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public int interestOps() {
                return SelectionKey.OP_ACCEPT | SelectionKey.OP_READ;
            }

            @Override
            public SelectionKey interestOps(int ops) {
                return null;
            }

            @Override
            public int readyOps() {
                return 0;
            }
        };
        final int interestOps = mockSelectionKey.interestOps();
        final boolean isInterestOpAccept = (interestOps & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;

        Assert.assertTrue("interest options should equals OP_ACCEPT", isInterestOpAccept);
    }

}

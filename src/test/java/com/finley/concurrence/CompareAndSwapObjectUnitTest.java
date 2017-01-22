package com.finley.concurrence;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 1/22/17.
 */
public class CompareAndSwapObjectUnitTest {

    @Test
    public void testCompareAndSwapObjectUnit() {
        CompareAndSwapObjectUnit compareAndSwapObjectUnit = new CompareAndSwapObjectUnit();
        CompareAndSwapObjectUnit.Node firstNode = new CompareAndSwapObjectUnit.Node();
        firstNode.setValue(100);
        compareAndSwapObjectUnit.compareAndSwapNext(null, firstNode);

        Assert.assertTrue(compareAndSwapObjectUnit.getNext() == firstNode);

        firstNode.setValue(200);
        CompareAndSwapObjectUnit.Node second = new CompareAndSwapObjectUnit.Node();
        compareAndSwapObjectUnit.compareAndSwapNext(firstNode, second);

        Assert.assertTrue(compareAndSwapObjectUnit.getNext() == second);
    }
}

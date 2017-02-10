package com.finley.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2/10/17.
 */
public class MathTest {

    @Test
    public void ceilTest() {
        Assert.assertEquals(1, Math.ceil(31 / 30), 0);
        Assert.assertEquals(2, Math.ceil(31 / 30.0), 0);
    }

}

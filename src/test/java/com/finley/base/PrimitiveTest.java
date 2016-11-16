package com.finley.base;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2016/11/16
 */
public class PrimitiveTest {

    @Test
    public void testToString() {
        final long testValue = 1000;
        System.out.println(((Object) testValue).getClass().getName());
        System.out.println(((Object) testValue).toString());
    }

}

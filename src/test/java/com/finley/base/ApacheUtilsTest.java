package com.finley.base;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2016/11/7
 */
public class ApacheUtilsTest {

    @Test
    public void testRandomStringUtils() {
        System.out.println(RandomStringUtils.randomAlphanumeric(6));
        System.out.println(RandomUtils.nextInt(2, 20));
        System.out.println(RandomStringUtils.randomNumeric(20));
    }

    @Test
    public void testBooleanUtils() {
        System.out.println(BooleanUtils.negate(true));
    }
}

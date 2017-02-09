package com.finley.base;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Defaults;

/**
 * @author fengjiantao.
 * @since 2/9/17.
 */
public class GuavaDefaultsTest {

    @Test
    public void testDefaultValue() {
        Assert.assertTrue("Default value should == 0.", 0 == Defaults.defaultValue(int.class));
    }
}

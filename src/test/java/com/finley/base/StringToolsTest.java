package com.finley.base;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.base.Splitter;

/**
 * @author fengjiantao.
 * @since 2016/11/16
 */
public class StringToolsTest {

    @Test
    public void testStringTools() {
        System.out.println(StringUtils.split("one,two,three,,", ",").length);
        System.out.println(Arrays.toString(StringUtils.split(" one  , two  , three  ,,", ",")));
        System.out.println(Splitter.on(",").omitEmptyStrings().trimResults().splitToList("one  ,two  ,   three,,"));
    }

    @Test
    public void performanceTest() {
        long begin = System.currentTimeMillis();
        for (int i = 0, len = 1000000; i < len; i++) {
            StringUtils.split("one,two,three,four,five,six,seven", ",");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
        begin = System.currentTimeMillis();
        for (int i = 0, len = 1000000; i < len; i++) {
            Splitter.on(",").omitEmptyStrings().trimResults().split("one,two,three,four,five,six,seven");
        }
        end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}

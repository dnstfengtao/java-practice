package com.finley.base;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;

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
        Stopwatch stopWatch = Stopwatch.createStarted();
        for (int i = 0, len = 1000000; i < len; i++) {
            StringUtils.split("one,two,three,four,five,six,seven", ",");
        }
        stopWatch.stop();
        System.out.println(stopWatch.elapsed(TimeUnit.MILLISECONDS));
        stopWatch.reset();
        stopWatch.start();
        for (int i = 0, len = 1000000; i < len; i++) {
            Splitter.on(",").omitEmptyStrings().trimResults().split("one,two,three,four,five,six,seven");
        }
        System.out.println(stopWatch.elapsed(TimeUnit.MILLISECONDS));
    }
}

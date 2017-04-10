package com.finley.logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author fengjiantao.
 * @since 2016/11/11
 */
public class LogTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testBasicLogging() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            logger.debug("basic test");
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testUsingMDC() {
        MDC.put("trackID", RandomStringUtils.randomAlphabetic(20).concat(StringUtils.SPACE));
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            logger.debug("test track id " + i);
        }
        System.out.println(System.currentTimeMillis() - start);
        MDC.clear();
    }

}

package com.finley.logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.Test;
import org.slf4j.MDC;

/**
 * @author fengjiantao.
 * @since 2016/11/11
 */
public class Log4j2Test {

    private final Logger logger = LogManager.getLogger();

    @Test
    public void testBasicLogging() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            logger.debug("basic test");
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testUsingThreadContext() {
        ThreadContext.put("trackID", RandomStringUtils.randomAlphabetic(20).concat(StringUtils.SPACE));
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            logger.debug("test track id " + i);
        }
        System.out.println(System.currentTimeMillis() - start);
        ThreadContext.clearAll();
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

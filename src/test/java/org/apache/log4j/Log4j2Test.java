package org.apache.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

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
            logger.error("test track id " + i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}

package ch.qos.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fengjiantao.
 * @since 2016/11/23
 */
public class LogBackTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testBasicLog() {
        logger.debug("Test logback {}", "log back content.");
    }
}

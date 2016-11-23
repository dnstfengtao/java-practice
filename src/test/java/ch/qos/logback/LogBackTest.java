package ch.qos.logback;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author fengjiantao.
 * @since 2016/11/23
 */
public class LogBackTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testBasicLog() {
        MDC.put("trackId", RandomStringUtils.randomAlphabetic(20).concat(StringUtils.SPACE));
        logger.debug("Test logback {}", "log back content.");
        MDC.clear();
    }
}

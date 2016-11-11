package org.apache.log4j;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.NOPLogger;
import org.apache.log4j.spi.NOPLoggerRepository;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2016/11/10
 */
public class Logging4J1Test {

    private static final String FQCN   = Category.class.getName();
    /**
     * logger.
     */
    private              Logger logger = Logger.getLogger(getClass());

    static {
        LogLog.setQuietMode(true);
    }

    @Test
    public void testLoggingEvent() {
        final LoggingEvent loggingEvent = new LoggingEvent(FQCN,
                new NOPLogger(new NOPLoggerRepository(), "notLogger"),
                Level.DEBUG,
                "debug the message",
                null);
        System.out.println(ToStringBuilder.reflectionToString(loggingEvent, ToStringStyle.SHORT_PREFIX_STYLE));
    }

    @Test
    public void testLog() {
        logger.debug("debug the message.");
    }

    @Test
    public void testMDC() {
        MDC.put("trackId", RandomStringUtils.randomNumeric(5) + "_" + RandomStringUtils.randomAlphanumeric(15) + "_");
        for (int i = 0; i < 1000; i++) {
            logger.debug("test track id " + i);
        }
        MDC.getContext().clear();
    }

    @Test
    public void testTime() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            logger.debug("test track id " + i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}

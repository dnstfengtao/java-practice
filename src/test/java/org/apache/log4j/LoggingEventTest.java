package org.apache.log4j;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.NOPLogger;
import org.apache.log4j.spi.NOPLoggerRepository;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2016/11/10
 */
public class LoggingEventTest {

    private static final String FQCN = Category.class.getName();

    @Test
    public void testLoggingEvent() {
        final LoggingEvent loggingEvent = new LoggingEvent(FQCN,
                new NOPLogger(new NOPLoggerRepository(), "notLogger"),
                Level.DEBUG,
                "debug the message",
                null);
        System.out.println(ToStringBuilder.reflectionToString(loggingEvent, ToStringStyle.SHORT_PREFIX_STYLE));
    }

}

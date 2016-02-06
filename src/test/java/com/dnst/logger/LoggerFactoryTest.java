package com.dnst.logger;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Finley
 */
public class LoggerFactoryTest {

    @Test
    public void getLogTest() {
        Log log = LoggerFactory.getLog(LoggerFactoryTest.class);
        Assert.assertNotNull("Cannot get null by logger factory.", log);
    }

}

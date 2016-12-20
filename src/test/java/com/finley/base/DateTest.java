package com.finley.base;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 12/19/16.
 */
public class DateTest {

    @Test
    public void simpleTest() {
        System.out.println(new Date().getTime());
    }

    @Test
    public void testDateParse() throws ParseException {
        System.out.println(DateUtils.parseDate("2016-12-01", "yyyy-MM-dd").getTime());
    }
}

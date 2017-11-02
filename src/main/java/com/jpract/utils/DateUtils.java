package com.jpract.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * 严格的日期转换setLenient(false); setLenient public void setLenient(boolean
 * lenient)指定日期/时间解析是否不严格。进行不严格解析时，解析程序可以使用启发式的方法来解释与此对象的格式不精确匹配的输入。进行严格解析时，
 * 输入必须匹配此对象的格式。 参数： lenient - 为 true 时，解析过程是不严格的 不会自动将错误日期转换为正确的日期
 * 例如:19450000,使用原DateUtil会转换为19441130
 */
public class DateUtils {
    public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";
    public static final String YM = "yyyyMM";
    public static final String YMD = "yyyyMMdd";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String NORMAL_DATE_FORMAT_NEW = "yyyy-mm-dd hh24:mi:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_ALL = "yyyyMMddHHmmssS";
    public static final String DATE_ALL_SIX = "yyyyMMddHHmmssSSSSSS";
    public static final String DATE_FORMAT_SLASH = "yyyy/MM/dd";
    public static final String DATETIME_FORMAT_SLASH = "yyyy/MM/dd HH:mm:ss";
    public static final String DATETIME_FORMAT_T = "yyyy-MM-ddTHHmmss";

    public static Long strDateToNum(String paramString) throws Exception {
        if (paramString == null)
            return null;
        String[] arrayOfString = null;
        String str = "";
        if (paramString.contains("-")) {
            arrayOfString = paramString.split("-");
            for (String anArrayOfString : arrayOfString) {
                str = str + anArrayOfString;
            }
            return Long.parseLong(str);
        }
        return Long.parseLong(paramString);
    }

    public static Long strDateToNum1(String paramString) throws Exception {
        if (paramString == null)
            return null;
        String[] arrayOfString = null;
        String str = "";
        if (paramString.contains("-")) {
            arrayOfString = paramString.split("-");
            for (String anArrayOfString : arrayOfString) {
                if (anArrayOfString.length() == 1)
                    str = str + "0" + anArrayOfString;
                else
                    str = str + anArrayOfString;
            }
            return Long.parseLong(str);
        }
        return Long.parseLong(paramString);
    }

    public static String numDateToStr(Long paramLong) {
        if (paramLong == null)
            return null;
        String str = null;
        str = paramLong.toString().substring(0, 4) + "-" + paramLong.toString().substring(4, 6) + "-"
                + paramLong.toString().substring(6, 8);
        return str;
    }

    public static Date stringToDate(String paramString1, String paramString2) throws RuntimeException {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString1);
        } catch (ParseException localParseException) {
            throw new RuntimeException("解析日期字符串时出错！");
        }
    }

    public static String dateToString(Date paramDate, String paramString) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString);
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static Date compactStringToDate(String paramString) {
        return stringToDate(paramString, "yyyyMMdd");
    }

    public static String dateToCompactString(Date paramDate) {
        return dateToString(paramDate, "yyyyMMdd");
    }

    public static String dateToNormalString(Date paramDate) {
        return dateToString(paramDate, "yyyy-MM-dd");
    }

    public static String dateToTString(Date paramDate) {
        return DateFormatUtils.format(paramDate, "yyyy-MM-dd") +
                "T" + DateFormatUtils.format(paramDate, "HHmmss");
    }

    public static String dateToMSecondString(Date paramDate) {
        return dateToString(paramDate, "yyyyMMddHHmmssSSS");
    }

    public static String compactStringDateToNormal(String paramString) throws Exception {
        return dateToNormalString(compactStringToDate(paramString));
    }

    public static int getDaysBetween(Date beginDate, Date endDate) {
        Objects.requireNonNull(beginDate);
        Objects.requireNonNull(endDate);

        Calendar beginCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        beginCalendar.setTime(beginDate);
        endCalendar.setTime(endDate);

        if (beginCalendar.after(endCalendar)) {
            // exchange.
            Calendar temp = beginCalendar;
            beginCalendar = endCalendar;
            endCalendar = temp;
        }

        int i = endCalendar.get(6) - beginCalendar.get(6);
        int j = endCalendar.get(1);
        if (beginCalendar.get(1) != j) {
            beginCalendar = (Calendar) beginCalendar.clone();
            do {
                i += beginCalendar.getActualMaximum(6);
                beginCalendar.add(1, 1);
            } while (beginCalendar.get(1) != j);
        }
        return i;
    }

    /**
     * 得到最近几天， 包含传入的 base day.
     *
     * @param baseDay 基准时间.
     * @param nDays   n 天数.
     * @return list of date.
     */
    public static List<Date> getLastFewDays(Date baseDay, int nDays) {
        Objects.requireNonNull(baseDay, "The input base day should not null");
        Preconditions.checkArgument(nDays >= 0, "The input before should not less than zero!");
        List<Date> result = Lists.newArrayList();
        for (int i = nDays - 1; i > 0; i--) {
            Date beforeDay = DateUtils.addDays(baseDay, -i);
            result.add(beforeDay);
        }
        // 增加 base day.
        result.add(baseDay);
        return result;
    }

    public static Date addDays(Date paramDate, int paramInt) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        int i = localCalendar.get(6);
        localCalendar.set(6, i + paramInt);
        return localCalendar.getTime();
    }

    public static Date addDays(String paramString1, String paramString2, int paramInt) {
        Calendar localCalendar = Calendar.getInstance();
        Date localDate = stringToDate(paramString1, paramString2);
        localCalendar.setTime(localDate);
        int i = localCalendar.get(6);
        localCalendar.set(6, i + paramInt);
        return localCalendar.getTime();
    }

    public static java.sql.Date getSqlDate(Date paramDate) throws Exception {
        return new java.sql.Date(paramDate.getTime());
    }

    public static String formatDate(Date paramDate, String pattern) {
        if (paramDate == null)
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(pattern);
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static String formatDate(Date paramDate) {
        if (paramDate == null)
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(NORMAL_DATE_FORMAT);
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static String formatCompactDate(Date paramDate) {
        if (paramDate == null)
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(COMPACT_DATE_FORMAT);
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static String formatDateTime(Date paramDate) {
        if (paramDate == null)
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static Date parse(String paramString, String pattern) {
        if ((paramString == null) || (paramString.trim().equals("")))
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(pattern);
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new IllegalArgumentException("The date parse error, the input param is illegal.");
        }
    }

    public static Date parseDate(String paramString) {
        if ((paramString == null) || (paramString.trim().equals("")))
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new IllegalArgumentException("Date parse error！", localParseException);
        }
    }

    public static Date parseDateTime(String paramString) {
        if ((paramString == null) || (paramString.trim().equals("")))
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new RuntimeException("时间解析异常！", localParseException);
        }
    }

    public static Integer getYM(String paramString) throws Exception {
        if (paramString == null)
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localSimpleDateFormat.setLenient(false);
        Date localDate;
        try {
            localDate = localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new Exception("时间解析异常！", localParseException);
        }
        return getYM(localDate);
    }

    /**
     * 得到当前年月.
     *
     * @param paramDate 日期对象.
     * @return 当前年月.
     */
    public static Integer getYM(Date paramDate) {
        Preconditions.checkNotNull(paramDate, "Argument paramDate cannot be null.");
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        final int year = localCalendar.get(Calendar.YEAR);
        final int month = localCalendar.get(Calendar.MONTH) + 1;
        return year * 100 + month;
    }

    public static Integer getYMD(Date paramDate) {
        Preconditions.checkNotNull(paramDate, "Argument paramDate cannot be null.");
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        final int year = localCalendar.get(Calendar.YEAR);
        final int month = localCalendar.get(Calendar.MONTH) + 1;
        final int day = localCalendar.get(Calendar.DATE);
        return year * 10000 + month * 100 + day;
    }

    /**
     * 得到当前年.
     *
     * @return 当前年.
     */
    public static int getCurrentYear() {
        return getYear(new Date());
    }

    /**
     * 得到当前天, YMD 形式 yyyyMMdd.
     *
     * @return current day ymd.
     */
    public static int getCurrentDayYMD() {
        return getYMD(new Date());
    }

    /**
     * 得到年份.
     *
     * @param date 日期.
     * @return 年份.
     */
    public static int getYear(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int addMonths(int paramInt1, int paramInt2) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.set(1, paramInt1 / 100);
        localCalendar.set(2, paramInt1 % 100 - 1);
        localCalendar.set(5, 1);
        localCalendar.add(2, paramInt2);
        return getYM(localCalendar.getTime());
    }

    public static Date addMonths(Date paramDate, int paramInt) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        localCalendar.add(2, paramInt);
        return localCalendar.getTime();
    }

    public static int monthsBetween(int paramInt1, int paramInt2) {
        return paramInt2 / 100 * 12 + paramInt2 % 100 - (paramInt1 / 100 * 12 + paramInt1 % 100);
    }

    public static int monthsBetween(Date paramDate1, Date paramDate2) {
        return monthsBetween(getYM(paramDate1), getYM(paramDate2));
    }

    public static String getChineseDate(Calendar paramCalendar) {
        int i = paramCalendar.get(1);
        int j = paramCalendar.get(2);
        int k = paramCalendar.get(5);
        return String.valueOf(i) +
                "年" + (j + 1) + "月" + k + "日";
    }

    public static String getChineseWeekday(Calendar paramCalendar) {
        switch (paramCalendar.get(7)) {
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            case 1:
                return "星期日";
        }
        return "未知";
    }

    /**
     * 设置某天的23:59:59
     *
     * @param date
     * @return
     */
    public static Date setLastTimeInOneDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * date 转 calendar
     *
     * @param date
     * @return
     */
    public static Calendar date2Cal(Date date) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(parseDate(formatDate(date)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static Date getDayBegin(Date date) {
        Objects.requireNonNull(date);
        return parse(formatDate(date) + " 00:00:00", DATETIME_FORMAT);
    }

    public static Date getDayEnd(Date date) {
        Objects.requireNonNull(date);
        return parse(formatDate(date) + " 23:59:59", DATETIME_FORMAT);
    }

    /**
     * 时分秒置为0
     *
     * @param date
     * @return
     */
    public static Date resetTimeZero(Date date) {
        SimpleDateFormat form = new SimpleDateFormat(DATE_FORMAT);
        return parse(form.format(date), DATE_FORMAT);
    }
}
package com.dnst.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Finley
 */
public class Log {
    // The class field.
    private String prefix;

    // The level prefix.
    private static final String DEBUG               = "debug";
    private static final String INFO                = "info";
    private static final String ERROR               = "error";
    private static final String WARN                = "warn";
    // The deep of method call
    private static final int    DEEP_OF_METHOD_CALL = 4;

    // The simple date formatter.
    private final DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");



    private Log(Class<?> clazz) {
        prefix = clazz.getSimpleName();
    }



    public static Log newInstance(Class<?> clazz) {
        return new Log(clazz);
    }



    public void debug(final String content) {
        output(DEBUG, content);
    }



    public void info(final Object content) {
        output(INFO, content);
    }



    public void error(final String content) {
        output(ERROR, content);
    }



    private CallerInfo getRunnerInfo() {
        final CallerInfo callerInfo = new CallerInfo();
        final StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        StackTraceElement callerMethodStack = null;
        if (stacks.length > DEEP_OF_METHOD_CALL + 1) {
            callerMethodStack = stacks[DEEP_OF_METHOD_CALL];
            callerInfo.setMethodName(callerMethodStack.getMethodName());
            callerInfo.setLine(callerMethodStack.getLineNumber());
        }
        return callerInfo;
    }



    public void warn(final String content) {
        output(WARN, content);
    }



    private void output(final String level, final Object content) {
        final String currentDate = DATE_FORMATTER.format(new Date());
        System.out.printf("%7s", "[" + level + "]");
        System.out.print(currentDate);
        final CallerInfo callerInfo = getRunnerInfo();
        System.out.printf("%50s", "  " + prefix + "##" + callerInfo.getMethodName());
        System.out.printf("%5d", callerInfo.getLine());
        System.out.print("  ");
        System.out.print(content);
        System.out.println();
    }



    class CallerInfo {
        private String methodName;
        private int    line;



        /**
         * @return the methodName
         */
        public String getMethodName() {
            return methodName;
        }



        /**
         * @param methodName the methodName
         */
        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }



        /**
         * @return the line
         */
        public int getLine() {
            return line;
        }



        /**
         * @param line the line
         */
        public void setLine(int line) {
            this.line = line;
        }
    }

}

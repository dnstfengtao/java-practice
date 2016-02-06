package com.dnst.logger;

/**
 * @author Finley
 */
public class LoggerFactory {

    public static Log getLog(Class<?> clazz) {
        return Log.newInstance(clazz);
    }



    public static void main(String[] args) {
        Log log = getLog(LoggerFactory.class);
        log.debug("some content");
        log.info("some content");
    }

}

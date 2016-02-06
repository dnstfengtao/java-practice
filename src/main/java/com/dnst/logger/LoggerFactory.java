package com.dnst.logger;

/**
 * @author Finley
 */
public class LoggerFactory {

    public static Log getLog(Class<?> clazz) {
        return Log.newInstance(clazz);
    }

}

package com.finley.base.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author fengjiantao.
 * @since 2016/11/9
 */
public class ClearProxy {
    private Object target;
    private Method method;

    private static final String CLEAR_METHOD_NAME = "clean";

    public ClearProxy(Object target) {
        this.target = target;
        try {
            method = target.getClass().getDeclaredMethod(CLEAR_METHOD_NAME);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void invoke() throws InvocationTargetException, IllegalAccessException {
        method.invoke(target);
    }
}

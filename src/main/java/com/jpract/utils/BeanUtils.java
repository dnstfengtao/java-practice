package com.jpract.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Throwables;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author fengjiantao.
 * @since 2016-11-17.
 */
public final class BeanUtils {

    /**
     * start date.
     */
    private static Date START_DATE;

    static {
        try {
            START_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1900-01-01 00:00:00");
        } catch (ParseException e) {
            Throwables.propagate(e);
        }
    }

    /**
     * 构造函数.
     */
    private BeanUtils() {
        throw new RuntimeException("This is util class,can not instance");
    }

    /**
     * 给传入参数对象传入默认值.
     *
     * @param obj 对象.
     */
    public static void notNull(final Object obj) {
        checkNotNull(obj);
        final Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (null == field.get(obj)) {
                    setFieldAsDefault(obj, obj.getClass(), field);
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }

    /**
     * 设置属性为默认值.
     *
     * @param obj   实际对象.
     * @param cla   类型.
     * @param field 属性 field 字段.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void setFieldAsDefault(Object obj, Class cla, Field field) throws Exception {
        Class type = field.getType();
        String fieldName = field.getName();
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method;
        if (String.class.equals(type)) {
            method = cla.getMethod(methodName, String.class);
            method.invoke(obj, StringUtils.EMPTY);
        } else if (BigDecimal.class.equals(type)) {
            method = cla.getMethod(methodName, BigDecimal.class);
            method.invoke(obj, BigDecimal.ZERO);
        } else if (Date.class.equals(type)) {
            method = cla.getMethod(methodName, Date.class);
            method.invoke(obj, START_DATE);
        } else if (long.class.equals(type)) {
            method = cla.getMethod(methodName, long.class);
            method.invoke(obj, 0);
        } else if (int.class.equals(type)) {
            method = cla.getMethod(methodName, int.class);
            method.invoke(obj, 0);
        } else if (Integer.class.equals(type)) {
            method = cla.getMethod(methodName, Integer.class);
            method.invoke(obj, 0);
        } else if (Long.class.equals(type)) {
            method = cla.getMethod(methodName, Long.class);
            method.invoke(obj, 0L);
        } else if (char.class.equals(type)) {
            method = cla.getMethod(methodName, char.class);
            method.invoke(obj, ' ');
        } else if (Character.class.equals(type)) {
            method = cla.getMethod(methodName, Character.class);
            method.invoke(obj, ' ');
        } else if (boolean.class.equals(type)) {
            method = cla.getMethod(methodName, boolean.class);
            method.invoke(obj, true);
        } else if (Boolean.class.equals(type)) {
            method = cla.getMethod(methodName, Boolean.class);
            method.invoke(obj, true);
        } else if (byte.class.equals(type)) {
            method = cla.getMethod(methodName, byte.class);
            method.invoke(obj, (byte) 0);
        } else if (Byte.class.equals(type)) {
            method = cla.getMethod(methodName, Byte.class);
            method.invoke(obj, (byte) 0);
        } else if (short.class.equals(type)) {
            method = cla.getMethod(methodName, short.class);
            method.invoke(obj, (short) 0);
        } else if (Short.class.equals(type)) {
            method = cla.getMethod(methodName, Short.class);
            method.invoke(obj, (short) 0);
        } else if (float.class.equals(type)) {
            method = cla.getMethod(methodName, float.class);
            method.invoke(obj, 0.0f);
        } else if (Float.class.equals(type)) {
            method = cla.getMethod(methodName, Float.class);
            method.invoke(obj, 0.0f);
        } else if (double.class.equals(type)) {
            method = cla.getMethod(methodName, double.class);
            method.invoke(obj, 0.0d);
        } else if (Double.class.equals(type)) {
            method = cla.getMethod(methodName, Double.class);
            method.invoke(obj, 0.0d);
        }
    }

}

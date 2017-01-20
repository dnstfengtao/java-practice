package com.finley.base.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.Unsafe;

/**
 * @author fengjiantao.
 * @since 1/20/17.
 */
public class UnsafeHolder {

    /**
     * Logger.
     */
    private static final Logger LOG                   = LoggerFactory.getLogger(UnsafeHolder.class);
    /**
     * Unsafe field name.
     */
    private static final String THE_UNSAFE_FIELD_NAME = "theUnsafe";
    /**
     * the unsafe instance.
     */
    private static Unsafe unsafe;

    private UnsafeHolder() {
        throw new UnsupportedOperationException("Cannot construct UnsafeHolder by reflection.");
    }

    static {
        try {
            final Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException ignore) {
            LOG.error("When get the field {} from the Unsafe class error occur.", THE_UNSAFE_FIELD_NAME, ignore);
        }
    }

    /**
     * Get the unsafe instance.
     *
     * @return unsafe instance.
     */
    public static Unsafe getUnsafe() {
        return unsafe;
    }

}

package com.finley.base;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.Test;

import com.finley.base.reflection.StaticFieldHolder;

/**
 * @author fengjiantao.
 * @since 2016/11/9
 */
public class StaticFieldHolderTest {
    @Test
    public void testChangeStaticWithReflect() throws Exception {
        System.out.println(Arrays.toString(StaticFieldHolder.class.getFields()));
        System.out.println(Arrays.toString(StaticFieldHolder.class.getDeclaredFields()));
        final Field field = StaticFieldHolder.class.getDeclaredField("value");
        System.out.println(Modifier.toString(field.getModifiers()));
        if (Modifier.isStatic(field.getModifiers())) {
            field.setAccessible(true);
        }
        field.set(StaticFieldHolder.class, "value-value");
        System.out.println(field.get(StaticFieldHolder.class));
        System.out.println(field.getType());
        System.out.println(field.isAccessible());
    }
}

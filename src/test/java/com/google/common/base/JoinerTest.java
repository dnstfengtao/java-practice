package com.google.common.base;

import java.lang.reflect.Constructor;
import java.util.AbstractList;
import java.util.Collections;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Iterables;

/**
 * @author fengjiantao.
 * @since 2016/11/24
 */
public class JoinerTest {

    static <T> Iterable<T> iterable(final T[] arrays) {
        if (ArrayUtils.isEmpty(arrays)) {
            return Collections.emptyList();
        }
        return new AbstractList<T>() {
            @Override
            public int size() {
                return arrays.length;
            }

            @Override
            public T get(int index) {
                return arrays[index];
            }
        };
    }

    @Test
    public void testConstruct() throws Exception {
        final Constructor<?>[] constructors = Joiner.class.getDeclaredConstructors();

        Constructor<?> findConstructor = Iterables.find(iterable(constructors), new Predicate<Object>() {
            @Override
            public boolean apply(Object input) {
                final Constructor<Joiner> joinerConstructor = (Constructor<Joiner>) input;

                Class<?> result = Iterables.find(iterable(joinerConstructor.getParameterTypes()),
                        new Predicate<Class<?>>() {
                            @Override
                            public boolean apply(Class<?> input) {
                                return String.class.isAssignableFrom(input);
                            }
                        });

                return result != null;
            }
        });

        findConstructor.setAccessible(true);
        Joiner joiner = (Joiner) findConstructor.newInstance(",");
        String result = joiner.join("a", "b", "c", "d");

        Assert.assertNotNull(result);
        Assert.assertEquals("a,b,c,d", result);
    }

}

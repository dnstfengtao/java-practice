package com.finley.cglib;

import net.sf.cglib.core.KeyFactory;

/**
 * @author fengjiantao.
 * @since 4/5/17.
 */
public class KeyFactoryTest {

    private interface MyFactory {
        Object newInstance(int a, char[] b, String d);
    }

    public static void main(String[] args) {
        MyFactory myFactory = (MyFactory) KeyFactory.create(MyFactory.class);
        Object key1 = myFactory.newInstance(2, new char[]{'a', 'b'}, "c");
        Object key2 = myFactory.newInstance(2, new char[]{'a', 'b'}, "c");
        Object key3 = myFactory.newInstance(20, new char[]{'a', 'b'}, "c");

        System.out.println(key1.equals(key2));
        System.out.println(key1.toString());
        System.out.println(key2.equals(key3));
    }
}

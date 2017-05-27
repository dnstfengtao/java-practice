package com.finley.proxy;

/**
 * @author fengjiantao.
 * @since 5/23/17.
 */
public class SimplePojo implements Pojo {
    @Override
    public void foo() {
        System.out.println("simple pojo foo.");
        foo1();
    }

    @Override
    public void foo1() {
        System.out.println("simple pojo foo1");
    }
}

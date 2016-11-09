package com.finley.base.reflection;

/**
 * @author fengjiantao.
 * @since 2016/11/9
 */
public class CacheClear implements Clearable {
    @Override
    public void clean() {
        System.out.println("clean,clean,clean");
    }
}

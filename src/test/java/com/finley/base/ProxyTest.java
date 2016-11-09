package com.finley.base;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.finley.base.reflection.CacheClear;
import com.finley.base.reflection.ClearProxy;

/**
 * @author fengjiantao.
 * @since 2016/11/9
 */
public class ProxyTest {
    @Test
    public void testProxy() {
        CacheClear cacheClear = new CacheClear();
        ClearProxy clearProxy = new ClearProxy(cacheClear);
        try {
            clearProxy.invoke();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

package com.finley.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author fengjiantao.
 * @since 5/23/17.
 */
public class ProxyTest {
    @Test
    public void testProxyFactory() {
//        ProxyFactory proxyFactory = new ProxyFactory(new SimplePojo());
//        proxyFactory.setExposeProxy(true);
//        proxyFactory.addAdvice((MethodInterceptor) invocation -> {
//            System.out.println("before");
//            return invocation.proceed();
//        });
//        proxyFactory.setProxyTargetClass(true);
//        Pojo pojo = (Pojo) proxyFactory.getProxy();
//        pojo.foo();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimplePojo.class);
        enhancer.setCallback((org.springframework.cglib.proxy.MethodInterceptor) (obj, method, args, methodProxy) -> {
            System.out.println("Before invoke " + method);
            Object result = methodProxy.invokeSuper(obj, args);
            System.out.println("After invoke" + method);
            return result;
        });
        Pojo simplePojo = (Pojo) enhancer.create();
        simplePojo.foo();
    }
}

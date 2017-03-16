package com.finley.proxy.primary;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fengjiantao.
 * @since 3/16/17.
 */
public class BankCardProxyFactory {

    public interface ProxyFactoryBean<T> extends InvocationHandler {

        void setTarget(T target);

    }

    public static class BankCardProxyService implements ProxyFactoryBean<BankCardService> {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private BankCardService target;

        public void setTarget(BankCardService target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            logger.info("before invoke");

            Object result = method.invoke(target, args);

            logger.info("after invoke");

            return result;
        }
    }


    public BankCardService getBankCardService(BankCardService original) {

        ProxyFactoryBean<BankCardService> proxyFactoryBean = new BankCardProxyService();
        proxyFactoryBean.setTarget(original);

        return (BankCardService) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{BankCardService.class}, proxyFactoryBean);
    }

}

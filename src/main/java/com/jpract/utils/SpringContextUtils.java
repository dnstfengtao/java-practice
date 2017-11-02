package com.jpract.utils;

import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

/**
 * 主要实现通过Spring非注入方式获取配置Bean.
 * <p>
 * 该组件必须以最小的 application context 来配置.
 *
 * @author fengjiantao.
 * @since 04/08/2016.
 */
public final class SpringContextUtils implements ApplicationContextAware {

    /**
     * 日志对象.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 应用程序上下文.
     */
    private static ApplicationContext applicationContext;

    public SpringContextUtils() {
        logger.info("SpringBeanUtils instance has be created.");
    }

    /**
     * 是否成功加载Spring上下文.
     *
     * @return true/false.
     */
    public static boolean isLoaded() {
        return null != applicationContext;
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static String[] getAliases(String name) {
        return applicationContext.getAliases(name);
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public static Object getBean(String name, Object... args) throws BeansException {
        return applicationContext.getBean(name, args);
    }

    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isPrototype(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException {
        return applicationContext.isTypeMatch(name, targetType);
    }

    public static void publishEvent(ApplicationEvent applicationEvent) {
        Objects.requireNonNull(applicationEvent, "The application event not null.");
        Objects.requireNonNull(applicationContext, "The application context require not null.");
        applicationContext.publishEvent(applicationEvent);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
        logger.info("Get applicationContext is ok, context id is {}", applicationContext.getId());
    }
}


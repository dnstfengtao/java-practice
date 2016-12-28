package com.finley.spring.beans;

import com.finley.spring.BasicBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author jiantao.feng
 * @since 7/26/16
 */
public class BeanFactoryTest {

    @Test
    public void testBeanFactory() {
        Resource resource = new ClassPathResource("beans.xml");
        BeanFactory factory = new XmlBeanFactory(resource);

        BasicBean basicBean = factory.getBean(BasicBean.class);
        System.out.println(basicBean.getName());
    }
}
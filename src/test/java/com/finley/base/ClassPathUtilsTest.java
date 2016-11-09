package com.finley.base;

import org.apache.commons.lang3.ClassPathUtils;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2016/11/9
 */
public class ClassPathUtilsTest {

    @Test
    public void testGetFullName() {
        System.out.println(ClassPathUtils.toFullyQualifiedName(ClassPathUtilsTest.class.getPackage(),
                "ClassPathUtilsTest"));
        System.out.println(ClassPathUtilsTest.class.getName());
    }

}

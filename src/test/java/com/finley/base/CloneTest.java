package com.finley.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2016/11/14
 */
public class CloneTest {

    private class A implements Cloneable {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        A a = new A();
        a.setName("a");
        A a1 = (A) a.clone();
        System.out.println(a1);
        System.out.println(a == a1);
    }

}

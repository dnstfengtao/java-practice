package com.finley.base.yaml;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.google.common.base.MoreObjects;

/**
 * @author fengjiantao.
 * @since 3/15/17.
 */
public class YAMLTest {
    public static class User {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("name", getName()).add("password", getPassword()).toString();
        }
    }

    @Test
    public void testYAML() {
        Yaml yaml = new Yaml();
        User user = new User();
        user.setName("Test");
        user.setPassword("123321");

        String content = yaml.dump(user);
        System.out.println(content);
        System.out.println(yaml.load(content));
    }
}

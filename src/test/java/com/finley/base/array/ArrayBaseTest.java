package com.finley.base.array;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 3/17/17.
 */
public class ArrayBaseTest {

    private class Unit {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void testArrayClone() {
        Unit[] units = new Unit[1];
        Unit unit = new Unit();
        units[0] = unit;

        Unit[] unitClones = units.clone();

        System.out.println(units);
        System.out.println(unitClones);
        System.out.println(units == unitClones);
        System.out.println(units[0] == unitClones[0]);
    }

}

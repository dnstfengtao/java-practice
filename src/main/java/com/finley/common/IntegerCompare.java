package com.finley.common;

/**
 * @author Finley
 */
public class IntegerCompare {
    public static void main(String[] args) {
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        if (i1 == i2) {
            System.out.println("equals1");
        }
        if (i1.equals(i2)) {
            System.out.println("sames.");
        }
        int i3 = 1;
        int i4 = 1;
        if (i3 == i4) {
            System.out.println("equals2");
        }
    }
}

package com.finley.base;

/**
 * @author fengjiantao.
 * @since 4/5/17.
 */
public class TestExtends {
    private static class A {
        public void a() {
            System.out.println("a");
            b();
        }

        public void b() {
            System.out.println("b");
        }
    }

    private static class B extends A {
        final void b$a() {
            super.a();
        }

        public void a() {
            System.out.println("before a");
            b$a();
            System.out.println("after a");
        }

        final void b$b() {
            super.b();
        }

        public void b() {
            System.out.println("before b");
            b$b();
            System.out.println("after b");
        }
    }

    public static void main(String[] args) {
        B b = new B();
        b.a();
    }
}

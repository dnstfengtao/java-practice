package com.finley.concurrence;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 1/20/17.
 */
public class AtomicIntUnBlockTest {
    @Test
    public void testAtomicIntUnBlock() {
        final AtomicIntUnBlock atomicIntBlock = new AtomicIntUnBlock();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0, len = 1000000; i < len; i++) {
                    atomicIntBlock.increment(1);
                }
            }
        }, "thread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0, len = 1000000; i < len; i++) {
                    atomicIntBlock.increment(1);
                }
            }
        }, "thread2");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0, len = 1000000; i < len; i++) {
                    atomicIntBlock.increment(1);
                }
            }
        }, "thread3");

        thread1.run();
        thread2.run();
        thread3.run();

        System.out.println(atomicIntBlock.get());
    }
}

package com.finley.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 1/20/17.
 */
public class AtomicIntUnBlockTest {
    @Test
    public void testAtomicIntUnBlock() throws InterruptedException {
        final AtomicIntUnBlock atomicIntUnBlock = new AtomicIntUnBlock();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100000; i ++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    atomicIntUnBlock.increment();
                }
            });
        }

        Thread.sleep(5000);
        System.out.println(atomicIntUnBlock.get());
    }
}

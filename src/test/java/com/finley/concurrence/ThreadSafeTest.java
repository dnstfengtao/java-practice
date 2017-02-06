package com.finley.concurrence;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2/6/17.
 */
public class ThreadSafeTest {

    private final static int W_CNT = 10;

    private static class ValueHolder {
        private int value;

        void inc() {
            value++;
        }

        int getValue() {
            return value;
        }
    }

    @Test
    public void testNotSafe() throws Exception {
        final ValueHolder valueHolder = new ValueHolder();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(W_CNT);

        for (int i = 0; i < W_CNT; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    for (int i = 0; i < 1000; i++) {
                        valueHolder.inc();
                    }

                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println(valueHolder.getValue());
    }

    @Test
    public void testSafeUnBlock() throws Exception {
        final AtomicInt atomicIntUnBlock = new AtomicIntUnBlock();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(W_CNT);

        addMultiTimes(atomicIntUnBlock, executorService, countDownLatch);
        countDownLatch.await();
        System.out.println(atomicIntUnBlock.get());
    }

    @Test
    public void testSafeBlock() throws Exception {
        final AtomicInt atomicIntBlock = new AtomicIntBlock();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(W_CNT);

        addMultiTimes(atomicIntBlock, executorService, countDownLatch);
        countDownLatch.await();
        System.out.println(atomicIntBlock.get());
    }

    private void addMultiTimes(final AtomicInt atomicIntUnBlock, ExecutorService executorService,
                               final CountDownLatch countDownLatch) {
        for (int i = 0; i < W_CNT; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        atomicIntUnBlock.increment();
                    }
                    countDownLatch.countDown();
                }
            });
        }
    }
}

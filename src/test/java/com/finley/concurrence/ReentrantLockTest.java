package com.finley.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2/6/17.
 */
public class ReentrantLockTest {
    private final ReentrantLock lock = new ReentrantLock();

    @Test
    public void testLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        });

        lock.lock();

    }
}

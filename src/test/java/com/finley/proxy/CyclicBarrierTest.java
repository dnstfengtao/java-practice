package com.finley.proxy;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

/**
 * Used for test the CyclicBarrier can stop the caller thread.
 *
 * @author fengjiantao.
 * @since 8/24/17.
 */
public class CyclicBarrierTest {

    public class Worker extends Thread {

        private CyclicBarrier barrier;

        public Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("before worker run.");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("after worker run.");
        }
    }

    @Test
    public void testCanMakeCallerThreadAwait() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            new Worker(cyclicBarrier).start();
        }

        System.out.println("The main thread run here....");

    }

}

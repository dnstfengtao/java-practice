package com.finley.concurrence;

import java.util.concurrent.locks.LockSupport;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 1/24/17.
 */
public class LockSupportTest {

    @Test
    public void testPark() {
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park(this);
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park(this);
        System.out.println(Thread.interrupted());
    }
}

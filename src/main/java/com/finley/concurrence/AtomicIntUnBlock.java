package com.finley.concurrence;

import com.finley.base.util.UnsafeHolder;

import sun.misc.Unsafe;

/**
 * @author fengjiantao.
 * @since 1/20/17.
 */
public class AtomicIntUnBlock {
    private static final Unsafe unsafe = UnsafeHolder.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(AtomicIntUnBlock.class.getDeclaredField("value"));
        } catch (NoSuchFieldException ignore) {
            throw new Error(ignore);
        }
    }

    private volatile int value;

    public int get() {
        return value;
    }

    public void increment(int incValue) {
        for (; ; ) {
            int current = get();
            int newValue = current + incValue;
            if (unsafe.compareAndSwapInt(this, valueOffset, current, newValue)) {
                return;
            }
        }
    }

    public void set(int value) {
        for (; ; ) {
            int current = get();
            if (unsafe.compareAndSwapInt(this, valueOffset, current, value)) {
                return;
            }
        }
    }
}

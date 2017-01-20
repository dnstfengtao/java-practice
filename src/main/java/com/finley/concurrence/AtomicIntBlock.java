package com.finley.concurrence;

/**
 * @author fengjiantao.
 * @since 1/20/17.
 */
public class AtomicIntBlock {
    private int value;

    public int get() {
        synchronized (this) {
            return value;
        }
    }

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public void set(int value) {
        synchronized (this) {
            this.value = value;
        }
    }
}

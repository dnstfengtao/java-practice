package com.finley.concurrence;

import com.finley.base.util.UnsafeHolder;

/**
 * @author fengjiantao.
 * @since 1/22/17.
 */
public class CompareAndSwapObjectUnit {

    private volatile     Node next;
    private static final long nextOffset;

    public void compareAndSwapNext(Node expected, Node update) {
        UnsafeHolder.getUnsafe().compareAndSwapObject(this, nextOffset, expected, update);
    }

    public Node getNext() {
        return next;
    }

    static {
        try {
            nextOffset = UnsafeHolder.getUnsafe().objectFieldOffset(CompareAndSwapObjectUnit.class.getDeclaredField
                    ("next"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    public static class Node {
        private volatile int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!Node.class.isAssignableFrom(obj.getClass())) {
                return false;
            }
            Node compareNode = (Node) obj;
            return this.value == compareNode.value;
        }
    }

}

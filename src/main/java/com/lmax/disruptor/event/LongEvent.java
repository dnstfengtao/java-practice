package com.lmax.disruptor.event;

import com.google.common.base.MoreObjects;

/**
 * @author fengjiantao.
 * @since 2016/11/23
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("value", value).toString();
    }
}

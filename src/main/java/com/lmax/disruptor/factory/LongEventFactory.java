package com.lmax.disruptor.factory;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.event.LongEvent;

/**
 * @author fengjiantao.
 * @since 2016/11/23
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}

package com.lmax.disruptor.event;

import com.lmax.disruptor.EventHandler;

/**
 * @author fengjiantao.
 * @since 2016/11/23
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event:" + event);
    }
}

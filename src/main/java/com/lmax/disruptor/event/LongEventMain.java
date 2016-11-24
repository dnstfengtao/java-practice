package com.lmax.disruptor.event;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.factory.LongEventFactory;

/**
 * @author fengjiantao.
 * @since 2016/11/23
 */
public class LongEventMain {

    public static void main(String[] args) {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newSingleThreadExecutor();

        int bufferSize = 1024;

        int repeatTime = 1000;

        Disruptor<LongEvent> disruptor = new Disruptor<>(new LongEventFactory(), bufferSize, executor);

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);

        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);

        for (long l = 0; l < repeatTime; l++) {
            bb.putLong(0, l);

            longEventProducer.onData(bb);
        }
    }
}

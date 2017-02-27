package com.finley.base.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2/27/17.
 */
public class FileChannelTest {

    @Test
    public void testBaseFileChannel() throws IOException {
        String filePath = getClass().getClassLoader().getResource("file.txt").getPath();
        System.out.println(filePath);
        final RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        final FileChannel inChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        int bytesRead = 0;

        List<Byte> byteList = new ArrayList<>();
        bytesRead = inChannel.read(byteBuffer);
        while (bytesRead != -1) {
            byteBuffer.flip();

            while (byteBuffer.hasRemaining()) {
                byteList.add(byteBuffer.get());
            }

            byteBuffer.clear();
            bytesRead = inChannel.read(byteBuffer);
        }

        final byte[] lines = new byte[byteList.size()];

        int[] idx = {0};
        byteList.forEach(e -> lines[idx[0]++] = e);

        System.out.println(new String(lines, CharEncoding.UTF_8));

        inChannel.close();
        file.close();
    }

}

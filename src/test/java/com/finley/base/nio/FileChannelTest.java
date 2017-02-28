package com.finley.base.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2/27/17.
 */
public class FileChannelTest {

    @Test
    public void testFileChannelReadData() throws IOException {
        String filePath = getClass().getClassLoader().getResource("file.txt").getPath();
        final RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        final FileChannel inChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        int bytesRead;

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

    @Test
    public void testFileChannelWriteData() throws IOException {
        String filePath = getClass().getClassLoader().getResource("file.txt").getPath();
        final RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        final FileChannel outChannel = file.getChannel();

        final String writeToFile = "New string to write to the file" + System.currentTimeMillis();
        final ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        byteBuffer.clear();
        byteBuffer.put(writeToFile.getBytes());

        byteBuffer.flip();

        outChannel.position(file.length());
        while (byteBuffer.hasRemaining()) {
            outChannel.write(byteBuffer);
        }

        outChannel.force(true);
        outChannel.close();
        file.close();
    }

}

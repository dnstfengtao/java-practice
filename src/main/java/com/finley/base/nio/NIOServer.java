package com.finley.base.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This NIO Server is for create an nio server socket and listen on port 9999.
 *
 * @author fengjiantao.
 * @since 2/27/17.
 */
public class NIOServer {

    private static final int    LISTEN_PORT = 9999;
    private final        Logger logger      = LoggerFactory.getLogger(getClass());
    /**
     * Server socket.
     */
    private ServerSocketChannel serverSocketChannel;

    /**
     * Start the NIO Server,
     * will first create server socket channel and bind port,
     * then will handle the request socket.
     *
     * @throws IOException io exception.
     */
    public void startUp() throws IOException {
        registerShutdown();

        createServerSocketChannelInstance();

        if (serverSocketChannel == null) {
            throw new IllegalStateException("The serverSocketChannel is not create.");
        }

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            // TODO handle the socket channel here.
        }
    }

    /**
     * Create the server socket bind on port {@link #LISTEN_PORT}.
     *
     * @throws IOException io exception.
     */
    private void createServerSocketChannelInstance() throws IOException {
        logger.info("Begin create server socket channel instance listen on port {}", LISTEN_PORT);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(LISTEN_PORT));
    }

    /**
     * Register shutdown hook for release the resources when the server closing.
     */
    private void registerShutdown() {
        logger.info("Register shutdown hook for nio server.");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (serverSocketChannel != null) {
                try {
                    logger.info("Execute serverSocketChannel close operation in thread {}", Thread.currentThread
                            ().getId());
                    serverSocketChannel.close();
                } catch (IOException e) {
                    logger.error("When shutdown the server close the serverSocketChannel error occur!!!",
                            serverSocketChannel);
                }
            }
        }));
    }

    public static void main(String[] args) throws IOException {
        new NIOServer().startUp();
    }
}

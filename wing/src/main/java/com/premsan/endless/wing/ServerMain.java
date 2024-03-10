/*
 * Copyright (c) PREMSAN
 * SPDX-License-Identifier: BUSL-1.1
 */
package com.premsan.endless.wing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.premsan.endless.base.Context;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public final class ServerMain {

    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));

    public static void main(String[] args) throws Exception {

        final Context context = new Context();
        final ObjectMapper objectMapper = new ObjectMapper();

        final EventLoopGroup parentGroup = new NioEventLoopGroup(1);
        final EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(
                            new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) {

                                    ChannelPipeline p = ch.pipeline();

                                    p.addLast(new HttpRequestDecoder());
                                    p.addLast(new HttpResponseEncoder());
                                    p.addLast(new HttpObjectAggregator(Short.MAX_VALUE));
                                    p.addLast(new InboundChannelHandler(context, objectMapper));
                                }
                            });

            serverBootstrap.bind(PORT).sync().channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}

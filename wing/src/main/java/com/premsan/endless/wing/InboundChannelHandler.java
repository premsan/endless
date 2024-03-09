/*
 * Copyright (c) PREMSAN
 * SPDX-License-Identifier: BUSL-1.1
 */
package com.premsan.endless.wing;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class InboundChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) {

        // Send response back so the browser won't timeout
        ByteBuf responseBytes = ctx.alloc().buffer();
        responseBytes.writeBytes("Hello World".getBytes());

        FullHttpResponse response =
                new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1, HttpResponseStatus.OK, responseBytes);
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
        response.headers()
                .set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());

        response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        ctx.writeAndFlush(response);
    }
}

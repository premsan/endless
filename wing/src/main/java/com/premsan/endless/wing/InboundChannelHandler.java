/*
 * Copyright (c) PREMSAN
 * SPDX-License-Identifier: BUSL-1.1
 */
package com.premsan.endless.wing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.premsan.endless.base.Context;
import com.premsan.endless.wing.handler.CreateConceptRequestHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class InboundChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final Set<FullHttpRequestHandler> requestHandlers = new HashSet<>();

    public InboundChannelHandler(final Context context, final ObjectMapper objectMapper) {

        this.requestHandlers.add(new CreateConceptRequestHandler(context, objectMapper));
    }

    @Override
    protected void channelRead0(
            final ChannelHandlerContext channelHandlerContext,
            final FullHttpRequest fullHttpRequest)
            throws IOException {

        for (final FullHttpRequestHandler requestHandler : requestHandlers) {
            if (requestHandler.handles(fullHttpRequest)) {

                requestHandler.handle(channelHandlerContext, fullHttpRequest);
            }
        }
    }
}

/*
 * Copyright (c) PREMSAN
 * SPDX-License-Identifier: BUSL-1.1
 */
package com.premsan.endless.wing;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import java.io.IOException;

public interface FullHttpRequestHandler {

    boolean handles(final FullHttpRequest fullHttpRequest);

    void handle(
            final ChannelHandlerContext channelHandlerContext,
            final FullHttpRequest fullHttpRequest)
            throws IOException;
}

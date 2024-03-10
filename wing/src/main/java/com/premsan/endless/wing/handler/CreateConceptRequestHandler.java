/*
 * Copyright (c) PREMSAN
 * SPDX-License-Identifier: BUSL-1.1
 */
package com.premsan.endless.wing.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.premsan.endless.base.Concept;
import com.premsan.endless.base.Context;
import com.premsan.endless.wing.FullHttpRequestHandler;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.io.IOException;
import java.io.InputStream;

public class CreateConceptRequestHandler implements FullHttpRequestHandler {

    private static final String URI = "/CreateConcept";

    private final Context context;
    private final ObjectMapper objectMapper;

    public CreateConceptRequestHandler(final Context context, final ObjectMapper objectMapper) {

        this.context = context;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean handles(final FullHttpRequest fullHttpRequest) {

        return fullHttpRequest.method() == HttpMethod.POST && fullHttpRequest.uri().equals(URI);
    }

    @Override
    public void handle(
            final ChannelHandlerContext channelHandlerContext,
            final FullHttpRequest fullHttpRequest)
            throws IOException {

        ByteBufInputStream byteBufInputStream = new ByteBufInputStream(fullHttpRequest.content());

        final CreateConceptBody body =
                objectMapper.readValue((InputStream) byteBufInputStream, CreateConceptBody.class);

        context.conceptRepository().add(new Concept.Builder().name(body.getName()));

        channelHandlerContext.writeAndFlush(
                new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK));
    }

    public static class CreateConceptBody {

        private String name;

        public String getName() {

            return this.name;
        }

        public void setName(final String name) {

            this.name = name;
        }
    }
}

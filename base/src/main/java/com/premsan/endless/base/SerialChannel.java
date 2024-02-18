/*
 * Copyright (c) PREMSAN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.premsan.endless.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public abstract class SerialChannel implements WritableByteChannel {

    private final ObjectMapper objectMapper = new ObjectMapperFactory().get();

    public int write(final Concept concept) throws IOException {

        return write(ByteBuffer.wrap(objectMapper.writeValueAsBytes(new SerialConcept(concept))));
    }

    public int write(final Node node) throws IOException {

        return write(ByteBuffer.wrap(objectMapper.writeValueAsBytes(new SerialNode(node))));
    }

    public int write(final Property<?> property) throws IOException {

        return write(ByteBuffer.wrap(objectMapper.writeValueAsBytes(new SerialProperty(property))));
    }
}

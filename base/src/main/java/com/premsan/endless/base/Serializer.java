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

public final class Serializer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Serial serial(final Concept concept) {

        return new SerialConcept(concept);
    }

    public Serial serial(final Node node) {

        return new SerialNode(node);
    }

    public Serial serial(final Property<?> property) {

        return new SerialProperty(property);
    }

    public String serialize(final Concept concept) throws IOException {

        return serialize(serial(concept));
    }

    public String serialize(final Node node) throws IOException {

        return serialize(serial(node));
    }

    public String serialize(final Property<?> property) throws IOException {

        return serialize(serial(property));
    }

    public String serialize(final Serial serial) throws IOException {

        return objectMapper.writeValueAsString(serial);
    }
}

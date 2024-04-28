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

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Property<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 4L;

    private final long creationTimeMillis;
    private final PropertySchema schema;
    private final UUID id;

    private final T value;

    public Property(
            final long creationTimeMillis,
            final PropertySchema schema,
            final UUID id,
            final T value) {
        Objects.requireNonNull(value, "value must not be null");

        this.creationTimeMillis = creationTimeMillis;
        this.schema = schema;
        this.id = id;
        this.value = value;
    }

    public long getCreationTimeMillis() {
        return creationTimeMillis;
    }

    public PropertySchema getSchema() {
        return schema;
    }

    public UUID getId() {
        return id;
    }

    public T getValue() {
        return value;
    }
}

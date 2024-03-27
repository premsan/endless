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

public class Property<T extends Serializable> implements Construct, Serializable {

    private static final long serialVersionUID = 3L;

    private final long creationTimeMillis;
    private final PropertySchema schema;
    private final Node node;
    private final UUID id;
    private final T value;

    public Property(
            final long creationTimeMillis,
            final PropertySchema schema,
            final Node node,
            final UUID id,
            final T value) {
        Objects.requireNonNull(node, "node must not be null");
        Objects.requireNonNull(value, "value must not be null");

        this.creationTimeMillis = creationTimeMillis;
        this.schema = schema;
        this.node = node;
        this.id = id;
        this.value = value;
    }

    @Override
    public long getCreationTimeMillis() {

        return this.creationTimeMillis;
    }

    public PropertySchema getSchema() {

        return this.schema;
    }

    public Node getNode() {

        return this.node;
    }

    @Override
    public UUID getId() {

        return this.id;
    }

    public T getValue() {

        return this.value;
    }

    public <C> C getValue(final Class<C> type) {

        return type.cast(this.value);
    }

    public static class Builder<T extends Serializable> {

        private long creationTimeMillis;
        private PropertySchema schema;
        private Node node;
        private UUID id;
        private T value;

        public Builder<T> creationTimeMillis(final long creationTimeMillis) {

            this.creationTimeMillis = creationTimeMillis;

            return this;
        }

        public Builder<T> schema(final PropertySchema schema) {

            this.schema = schema;

            return this;
        }

        public Builder<T> node(final Node node) {
            Objects.requireNonNull(node, "node must not be null");

            this.node = node;

            return this;
        }

        public Builder<T> id(final UUID id) {
            Objects.requireNonNull(id, "id must not be null");

            this.id = id;

            return this;
        }

        public Builder<T> value(final T value) {
            Objects.requireNonNull(value, "value must not be null");

            this.value = value;

            return this;
        }

        public Property<T> build() {

            return new Property<>(
                    this.creationTimeMillis, this.schema, this.node, this.id, this.value);
        }
    }
}

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

    private static final long serialVersionUID = 2L;

    private final UUID id;

    private final long ts;

    private final Node node;

    private final String name;

    private final Class<T> dataType;

    private final T value;

    public Property(
            final UUID id,
            final long ts,
            final Node node,
            final String name,
            final Class<T> dataType,
            final T value) {
        Objects.requireNonNull(node, "node must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(dataType, "type must not be null");
        Objects.requireNonNull(value, "value must not be null");

        this.id = id;

        this.ts = ts;

        this.node = node;

        this.name = name;

        this.dataType = dataType;

        this.value = value;
    }

    @Override
    public UUID getId() {

        return this.id;
    }

    @Override
    public long getTs() {

        return this.ts;
    }

    public Node getNode() {

        return this.node;
    }

    public Class<T> getDataType() {

        return this.dataType;
    }

    public String getName() {

        return this.name;
    }

    public T getValue() {

        return this.value;
    }

    public <C> C getValue(final Class<C> type) {

        return type.cast(this.value);
    }

    public static class Builder<T extends Serializable> {

        private UUID id;

        private long ts;

        private Node node;

        private String name;

        private Class<T> dataType;

        private T value;

        public Builder<T> id(final UUID id) {
            Objects.requireNonNull(id, "id must not be null");

            this.id = id;

            return this;
        }

        public Builder<T> ts(final long ts) {

            this.ts = ts;

            return this;
        }

        public Builder<T> node(final Node node) {
            Objects.requireNonNull(node, "node must not be null");

            this.node = node;

            return this;
        }

        public Builder<T> name(String name) {
            Objects.requireNonNull(name, "id must not be null");

            this.name = name;

            return this;
        }

        public Builder<T> dataType(final Class<T> dataType) {
            Objects.requireNonNull(dataType, "dataType must not be null");

            this.dataType = dataType;

            return this;
        }

        public Builder<T> value(final T value) {
            Objects.requireNonNull(value, "value must not be null");

            this.value = value;

            return this;
        }

        public Property<T> build() {

            return new Property<>(
                    this.id, this.ts, this.node, this.name, this.dataType, this.value);
        }
    }
}

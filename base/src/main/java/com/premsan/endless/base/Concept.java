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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public final class Concept implements Construct, Serializable {

    private static final long serialVersionUID = 1L;

    private final long creationTimeMillis;
    private final UUID id;
    private final String name;
    private final List<PropertySchema> propertySchemaList;

    private final Set<Node> nodes = new HashSet<>();

    private Concept(
            final long creationTimeMillis,
            final UUID id,
            final String name,
            final List<PropertySchema> propertySchemaList) {

        this.creationTimeMillis = creationTimeMillis;
        this.id = id;
        this.name = name;
        this.propertySchemaList = propertySchemaList;
    }

    @Override
    public long getCreationTimeMillis() {

        return this.creationTimeMillis;
    }

    @Override
    public UUID getId() {

        return this.id;
    }

    public String getName() {

        return this.name;
    }

    public List<PropertySchema> getPropertySchemaList() {

        return this.propertySchemaList;
    }

    synchronized void addNode(final Node node) {

        this.nodes.add(node);
    }

    public Set<Node> getNodes() {

        return this.nodes;
    }

    public static class Builder {

        private long creationTimeMillis;
        private UUID id;
        private String name;
        private List<PropertySchema> propertySchemaList;

        public Builder creationTimeMillis(final long creationTimeMillis) {

            this.creationTimeMillis = creationTimeMillis;

            return this;
        }

        public Builder id(final UUID id) {
            Objects.requireNonNull(id, "id must not be null");

            this.id = id;

            return this;
        }

        public Builder name(final String name) {

            this.name = name;

            return this;
        }

        public Builder propertySchemaList(final List<PropertySchema> propertySchemaList) {

            this.propertySchemaList = propertySchemaList;

            return this;
        }

        public Concept build() {

            return new Concept(this.creationTimeMillis, this.id, this.name, propertySchemaList);
        }
    }
}

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
import java.util.Set;
import java.util.UUID;

public class Node implements Construct, Serializable {

    private static final long serialVersionUID = 2L;

    private final long creationTimeMillis;
    private final Concept concept;
    private final UUID id;
    private final Set<Property<?>> properties;

    private Node(
            final long creationTimeMillis,
            final Concept concept,
            final UUID id,
            final Set<Property<?>> properties) {

        this.creationTimeMillis = creationTimeMillis;

        this.concept = concept;
        this.concept.addNode(this);

        this.id = id;
        this.properties = properties;
    }

    @Override
    public long getCreationTimeMillis() {

        return this.creationTimeMillis;
    }

    @Override
    public UUID getId() {

        return this.id;
    }

    public Concept getConcept() {

        return this.concept;
    }

    public Set<Property<?>> getProperties() {

        return this.properties;
    }

    public synchronized void addProperty(final Property<?> property) {
        Objects.requireNonNull(property, "property must not be null");

        this.properties.add(property);
    }

    public static class Builder {

        private long creationTimeMillis;
        private Concept concept;
        private UUID id;
        private Set<Property<?>> properties;

        public Builder creationTimeMillis(final long creationTimeMillis) {

            this.creationTimeMillis = creationTimeMillis;

            return this;
        }

        public Builder concept(final Concept concept) {
            Objects.requireNonNull(concept, "concept must not be null");

            this.concept = concept;

            return this;
        }

        public Builder id(final UUID id) {
            Objects.requireNonNull(id, "id must not be null");

            this.id = id;

            return this;
        }

        public Builder properties(final Set<Property<?>> properties) {
            Objects.requireNonNull(properties, "properties must not be null");

            this.properties = properties;

            return this;
        }

        public Node build() {

            return new Node(this.creationTimeMillis, this.concept, this.id, this.properties);
        }
    }
}

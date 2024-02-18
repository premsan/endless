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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Node implements Construct, Serializable {

    private static final long serialVersionUID = 1L;

    private final UUID id;

    private final long ts;

    private final Concept concept;

    private final Map<Node, String> parents;

    private final Map<String, Property<?>> properties = new HashMap<>();

    private final Set<Property<?>> exProperties = new HashSet<>();

    private final Set<Node> children = new HashSet<>();

    private Node(
            final UUID id, final long ts, final Concept concept, final Map<Node, String> parents) {

        this.id = id;

        this.ts = ts;

        this.concept = concept;
        this.concept.addNode(this);

        this.parents = parents;
        for (final Node node : parents.keySet()) {
            node.addChild(this);
        }
    }

    @Override
    public UUID getId() {

        return this.id;
    }

    @Override
    public long getTs() {

        return this.ts;
    }

    public Concept getConcept() {

        return this.concept;
    }

    public Map<Node, String> getParents() {

        return this.parents;
    }

    public Set<Property<?>> getExProperties() {

        return this.exProperties;
    }

    public synchronized void setProperty(final Property<?> property) {
        Objects.requireNonNull(property, "property must not be null");

        Property<?> property0 = this.properties.get(property.getName());
        this.properties.put(property.getName(), property);

        if (property0 != null) {
            this.exProperties.add(property0);
        }
    }

    public Map<String, Property<?>> getProperties() {

        return this.properties;
    }

    public Property<?> getProperty(final String name) {
        Objects.requireNonNull(name, "name must not be null");

        return this.properties.get(name);
    }

    synchronized void addChild(final Node child) {

        this.children.add(child);
    }

    Set<Node> getChildren() {

        return this.children;
    }

    public static class Builder {

        private UUID id;

        private long ts;

        private Concept concept;

        private final Map<Node, String> parents = new HashMap<>();

        public Builder id(final UUID id) {
            Objects.requireNonNull(id, "id must not be null");

            this.id = id;

            return this;
        }

        public Builder ts(final long ts) {
            Objects.requireNonNull(ts, "ts must not be null");

            this.ts = ts;

            return this;
        }

        public Builder concept(final Concept concept) {
            Objects.requireNonNull(concept, "concept must not be null");

            this.concept = concept;

            return this;
        }

        public Builder addParent(final Node parent, final String role) {
            Objects.requireNonNull(parent, "parent must not be null");
            Objects.requireNonNull(role, "role must not be null");

            this.parents.put(parent, role);

            return this;
        }

        public Node build() {

            return new Node(this.id, this.ts, this.concept, this.parents);
        }
    }
}

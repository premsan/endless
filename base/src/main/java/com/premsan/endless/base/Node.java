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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class Node {

    private final String id;

    private final Concept concept;

    private final Map<Node, String> parents;

    private final Map<String, Value<?>> properties = new HashMap<>();

    private final Map<Value<?>, String> oldProperties = new HashMap<>();

    private final Set<Node> children = new HashSet<>();

    Node(final String id, final Concept concept, final Map<Node, String> parents) {

        this.id = id;

        this.concept = concept;
        this.concept.addNode(this);

        this.parents = parents;
        for (final Node node : parents.keySet()) {
            node.addChild(this);
        }
    }

    public String getId() {

        return this.id;
    }

    public Concept getConcept() {

        return this.concept;
    }

    public Map<Node, String> getParents() {

        return this.parents;
    }

    public void setProperty(final String name, final Value<?> value) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(value, "value must not be null");

        this.properties.put(name, value);
    }

    public Map<String, Value<?>> getProperties() {

        return this.properties;
    }

    public Value<?> getProperty(final String name) {
        Objects.requireNonNull(name, "name must not be null");

        return this.properties.get(name);
    }

    void addChild(final Node child) {

        this.children.add(child);
    }

    Set<Node> getChildren() {

        return this.children;
    }
}

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
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class NodeBuilder {

    private UUID id;

    private Concept concept;

    private final Map<Node, String> parents = new HashMap<>();

    NodeBuilder id(final UUID id) {
        Objects.requireNonNull(id, "id must not be null");

        this.id = id;

        return this;
    }

    public NodeBuilder concept(final Concept concept) {
        Objects.requireNonNull(concept, "concept must not be null");

        this.concept = concept;

        return this;
    }

    public NodeBuilder addParent(final Node parent, final String role) {
        Objects.requireNonNull(parent, "parent must not be null");
        Objects.requireNonNull(role, "role must not be null");

        this.parents.put(parent, role);

        return this;
    }

    Node build() {

        return new Node(this.id, this.concept, this.parents);
    }
}

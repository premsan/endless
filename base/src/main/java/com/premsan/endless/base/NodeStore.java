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

public final class NodeStore {

    private final Map<String, Node> nodes = new HashMap<>();

    public void store(final Node node) {
        Objects.requireNonNull(node, "node must not be null");

        this.nodes.put(node.getId(), node);
    }

    public Node find(final String id) {
        Objects.requireNonNull(id, "id must not be null");

        return this.nodes.get(id);
    }
}

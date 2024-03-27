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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class NodeRepository {

    private final Map<UUID, Node> nodeMap = new HashMap<>();

    private final ConceptRepository conceptRepository;
    private SerialChannel serialChannel;

    public NodeRepository(final ConceptRepository conceptRepository) {

        this.conceptRepository = conceptRepository;
    }

    public NodeRepository(
            final ConceptRepository conceptRepository, final SerialChannel serialChannel) {

        this.conceptRepository = conceptRepository;
        this.serialChannel = serialChannel;
    }

    public synchronized Node add(final Node.Builder nodeBuilder) {
        Objects.requireNonNull(nodeBuilder, "node must not be null");

        final Node node =
                nodeBuilder
                        .id(UUID.randomUUID())
                        .creationTimeMillis(System.currentTimeMillis())
                        .build();

        this.nodeMap.put(node.getId(), node);

        if (serialChannel != null) {

            try {

                serialChannel.write(node);

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }

        return node;
    }

    public Node find(final UUID id) {
        Objects.requireNonNull(id, "id must not be null");

        return this.nodeMap.get(id);
    }

    Map<UUID, Node> nodeMap() {

        return this.nodeMap;
    }

    public void load(final SerialNode serialNode) {

        final Concept concept = conceptRepository.find(serialNode.getId());

        final Node.Builder builder =
                new Node.Builder()
                        .creationTimeMillis(serialNode.getCreationTimeMillis())
                        .id(serialNode.getId())
                        .concept(concept);

        final Node node = builder.build();

        this.nodeMap.put(node.getId(), node);
    }
}

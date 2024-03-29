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
import java.util.UUID;

public class SerialNode implements Serial {

    private UUID id;

    private long ts;

    private String conceptName;

    private final Map<UUID, String> parents = new HashMap<>();

    private SerialNode() {}

    public SerialNode(final Node node) {

        this.id = node.getId();

        this.ts = node.getTs();

        this.conceptName = node.getConcept().getName();

        for (final Map.Entry<Node, String> parentEntry : node.getParents().entrySet()) {

            parents.put(parentEntry.getKey().getId(), parentEntry.getValue());
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

    public String getConceptName() {

        return this.conceptName;
    }

    public Map<UUID, String> getParents() {

        return this.parents;
    }
}

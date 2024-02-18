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
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public final class Concept implements Construct, Serializable {

    private static final long serialVersionUID = 3L;

    private final UUID id;

    private final long ts;

    private final String name;

    private final Set<Node> nodeSet = new HashSet<>();

    private Concept(final UUID id, final long ts, final String name) {

        this.id = id;

        this.ts = ts;

        this.name = name;
    }

    @Override
    public UUID getId() {

        return this.id;
    }

    @Override
    public long getTs() {

        return this.ts;
    }

    public String getName() {

        return this.name;
    }

    synchronized void addNode(final Node node) {

        this.nodeSet.add(node);
    }

    public Set<Node> getNodes() {

        return this.nodeSet;
    }

    public static class Builder {

        private UUID id;

        private long ts;

        private String name;

        public Builder id(final UUID id) {
            Objects.requireNonNull(id, "id must not be null");

            this.id = id;

            return this;
        }

        public Builder ts(final long ts) {

            this.ts = ts;

            return this;
        }

        public Builder name(final String name) {

            this.name = name;

            return this;
        }

        public Concept build() {

            return new Concept(this.id, this.ts, this.name);
        }
    }
}

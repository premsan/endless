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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class Node {

    private final String id;

    private final Set<String> tags;

    private final Map<Node, String> parentRoleMap;

    private final Set<Property<?>> properties = new HashSet<>();

    private Node(final String id, final Set<String> tags, final Map<Node, String> parentRoleMap) {

        this.id = id;
        this.tags = tags;
        this.parentRoleMap = parentRoleMap;
    }

    public String id() {

        return this.id;
    }

    public Set<String> tags() {

        return Collections.unmodifiableSet(this.tags);
    }

    public Node addTag(final String tag) {

        this.tags.add(Objects.requireNonNull(tag, "tag must not be null"));
        return this;
    }

    public Node removeTag(final String tag) {

        this.tags.remove(Objects.requireNonNull(tag, "tag must not be null"));
        return this;
    }

    public Map<Node, String> parentRoleMap() {

        return Collections.unmodifiableMap(this.parentRoleMap);
    }

    public Set<Property<?>> properties() {

        return Collections.unmodifiableSet(this.properties);
    }

    public Node addProperty(final Property<?> property) {

        this.properties.add(Objects.requireNonNull(property, "tag must not be null"));
        return this;
    }

    public Node removeProperty(final Property<?> property) {

        this.properties.remove(property);
        return this;
    }

    public Node clearProperties() {

        this.properties.clear();
        return this;
    }

    public static class Builder {

        private String id;

        private Set<String> tags;

        private Map<Node, String> parentRoleMap;

        public Builder id(final String id) {

            this.id = Objects.requireNonNull(id, "id must not be null");
            return this;
        }

        public Builder tags(final Set<String> tags) {

            this.tags = Objects.requireNonNull(tags, "tags must not be null");
            return this;
        }

        public Builder parentRoleMap(final Map<Node, String> parentRoleMap) {

            this.parentRoleMap =
                    Objects.requireNonNull(parentRoleMap, "parentRoleMap must not be null");
            return this;
        }

        public Node build() {

            if (this.tags == null) {
                this.tags = new HashSet<>();
            }

            if (this.parentRoleMap == null) {
                this.parentRoleMap = new HashMap<>();
            }

            return new Node(this.id, this.tags, this.parentRoleMap);
        }
    }
}

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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Node {

    private final String id;

    private final Set<String> tags;

    public Node(final String id) {

        this.id = Objects.requireNonNull(id, "id must not be null");
        this.tags = new HashSet<>();
    }

    public Node(final String id, final Set<String> tags) {

        this.id = Objects.requireNonNull(id, "id must not be null");
        this.tags = Objects.requireNonNull(tags, "tags must not be null");
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
}

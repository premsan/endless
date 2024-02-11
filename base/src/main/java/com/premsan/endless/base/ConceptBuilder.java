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

import java.util.Objects;
import java.util.UUID;

public class ConceptBuilder {

    private UUID _id;

    private String id;

    ConceptBuilder _id(final UUID _id) {

        this._id = _id;

        return this;
    }

    public ConceptBuilder id(final String id) {
        Objects.requireNonNull(id, "id must not be null");

        this.id = id;

        return this;
    }

    Concept build() {

        return new Concept(_id, id);
    }
}
